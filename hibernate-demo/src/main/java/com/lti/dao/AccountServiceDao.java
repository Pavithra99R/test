package com.lti.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Activity;

public class AccountServiceDao extends GenericDao {

	public List<Activity> fetchMiniStatement(int acno){
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			Query q = em.createQuery("select a from Activity a where a.account.accountNo = :cr order by a.dateAndTime desc");
			q.setParameter("cr", acno);
			q.setMaxResults(5);
			List<Activity> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}
	}
	
	public List<Object[]> fetchAccountDetails(int acno,LocalDate transactionDate){
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			//sql
//			select a.name,t.amount,t.dateandtime from tbl_account a join
//			tbl_activity t on a.accountno= t.account_no 
//			where a.accountno=125 and
//			to_char(t.dateandtime,'DD-MM-YYYY') = '29-12-2020'; 
			Query q = em.createQuery("select acc.name,tx.amount,tx.type from Account acc "
					+ "join acc.activities tx where acc.accountNo = :cr ");
			q.setParameter("cr", acno);
			List<Object[]> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}
	}
	
	public List<Object[]> fetchUsersBasedOnAccountType(String type){
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();
 
			Query q = em.createQuery("select acc.name,acc.balance from Account acc where acc.type = :var");
			q.setParameter("var", type);
			List<Object[]> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}
	}
}
