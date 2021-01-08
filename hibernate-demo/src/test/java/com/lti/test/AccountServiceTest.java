package com.lti.test;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.lti.entity.Account;
import com.lti.entity.Activity;
import com.lti.exception.AccountException;
import com.lti.service.AccountService;

public class AccountServiceTest {

	@Test
	public void testingOfopeningAnAccountPositive() {
		Account acc = new Account();
		acc.setName("Shreya");
		acc.setBalance(10000);
		acc.setType("Savings");
		
		AccountService accServ = new AccountService();
		int accno = accServ.openAccount(acc);
		assertTrue(accno > 0);
	}
	
	@Test(expected = AccountException.class)
	public void testingOfopeningAnAccountNegative() {
		Account acc = new Account();
		acc.setName("Pavi");
		acc.setBalance(900);
		acc.setType("Savings");
		
		AccountService accServ = new AccountService();
		accServ.openAccount(acc);
	}
	
	@Test
	public void testingOfWidthdrawing() {
		
		AccountService acc = new AccountService();
		int acno = 125;
		double currentBalance = acc.balanceCheck(acno);
		double amountToWithdraw = 1000;
		double expectedBalance = currentBalance - amountToWithdraw;
		acc.withdraw(acno, amountToWithdraw);
		double actualBalance = acc.balanceCheck(acno);
		//assertEquals(expectedBalance,actualBalance);
		assertTrue(expectedBalance == actualBalance);
	}
	
	@Test
	public void testingOfBalanceCheck() {
		
		AccountService acc = new AccountService();
		System.out.println(acc.balanceCheck(125));
	}
	
	@Test
	public void testingOfDeposit() {
		
		AccountService acc = new AccountService();
		int acno = 125;
		double currentBalance = acc.balanceCheck(acno);
		double amountToDeposit = 1000;
		double expectedBalance = currentBalance + amountToDeposit;
		acc.deposit(acno, amountToDeposit);
		double actualBalance = acc.balanceCheck(acno);
		//assertEquals(expectedBalance,actualBalance);
		assertTrue(expectedBalance == actualBalance);
	}
	
	@Test
	public void testingOfTransfer() {
		
		AccountService acc = new AccountService();
		acc.transfer(125, 126, 4000);
	}
	
	@Test
	public void testingOfMiniStatement() {
		
		AccountService acc = new AccountService();
		List<Activity> activity= acc.miniStatement(125);
		for(Activity a:activity) {
			System.out.println(a.getTxno()+", "+a.getType()+", "+a.getAmount()+", "+a.getDateAndTime()+", "+a.getAccount().getAccountNo());
		}
	}
	
	@Test
	public void testingOfAccountDetails() {
		
		AccountService acc = new AccountService();
		List<Object[]> activity= acc.accountInfo(125, LocalDate.of(2020, 12, 29));
		for(Object[] a:activity) {
			System.out.println(a[0]+", "+a[1]+", "+a[2]);
		}
	}
	
	@Test
	public void testingOfAccountType() {
		
		AccountService acc = new AccountService();
		List<Object[]> activity= acc.accountTypeInfo("Savings");
		for(Object[] a:activity) {
			System.out.println(a[0]+", "+a[1]);
		}
	}
}
