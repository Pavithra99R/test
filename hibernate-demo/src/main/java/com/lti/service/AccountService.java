package com.lti.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.lti.dao.AccountServiceDao;
import com.lti.entity.Account;
import com.lti.entity.Activity;
import com.lti.exception.AccountException;

public class AccountService {

	public int openAccount(Account acc) {
		if (acc.getBalance() >= 10000) {
			AccountServiceDao dao = new AccountServiceDao();
			Account newAcc = (Account) dao.save(acc);
			return newAcc.getAccountNo();
		} else {
			throw new AccountException("Sorry not enough balance to open a account!");
		}
	}

	public void withdraw(int acno, double amount) {
		AccountServiceDao dao = new AccountServiceDao();
		Account newAcc = (Account) dao.fetch(Account.class, acno);
		if (newAcc.getBalance() > amount) {

			Activity activity = new Activity();
			activity.setType("Withdraw");
			activity.setAmount(amount);
			activity.setDateAndTime(LocalDateTime.now());

			activity.setAccount(newAcc);

			newAcc.setBalance(newAcc.getBalance() - amount);

			dao.save(newAcc);
			dao.save(activity);

		} else {
			throw new AccountException("Sorry not enough balance!");
		}

	}

	public void deposit(int acno, double amount) {

		AccountServiceDao dao = new AccountServiceDao();
		Account newAcc = (Account) dao.fetch(Account.class, acno);

		Activity activity = new Activity();
		activity.setType("Deposit");
		activity.setAmount(amount);
		activity.setDateAndTime(LocalDateTime.now());

		activity.setAccount(newAcc);

		newAcc.setBalance(newAcc.getBalance() + amount);

		dao.save(newAcc);
		dao.save(activity);

	}

	public void transfer(int fromAcno, int toAcno, double amount) {

		AccountServiceDao dao = new AccountServiceDao();
		Account fromAcc = (Account) dao.fetch(Account.class, fromAcno);
		Account toAcc = (Account) dao.fetch(Account.class, toAcno);
		if (fromAcc.getBalance() > amount) {

			Activity activity = new Activity();
			activity.setType("Fund Transfer");
			activity.setAmount(amount);
			activity.setDateAndTime(LocalDateTime.now());
			activity.setAccount(fromAcc);

			fromAcc.setBalance(fromAcc.getBalance() - amount);

			dao.save(fromAcc);
			dao.save(activity);

			Activity activity1 = new Activity();
			activity1.setType("Fund Deposited");
			activity1.setAmount(amount);
			activity1.setDateAndTime(LocalDateTime.now());
			activity1.setAccount(toAcc);

			toAcc.setBalance(toAcc.getBalance() + amount);

			dao.save(toAcc);
			dao.save(activity1);

		} else {
			throw new AccountException("Sorry not enough balance!");
		}

	}

	public double balanceCheck(int acno) {
		AccountServiceDao dao = new AccountServiceDao();
		Account newAcc = (Account) dao.fetch(Account.class, acno);
		return newAcc.getBalance();
	}

	public List<Activity> miniStatement(int acno) {

		AccountServiceDao dao = new AccountServiceDao();
		return dao.fetchMiniStatement(acno);
	}

	public List<Object[]> accountInfo(int acno, LocalDate date) {

		AccountServiceDao dao = new AccountServiceDao();
		return dao.fetchAccountDetails(acno, date);
	}

	public List<Object[]> accountTypeInfo(String type) {

		AccountServiceDao dao = new AccountServiceDao();
		return dao.fetchUsersBasedOnAccountType(type);
	}
}
