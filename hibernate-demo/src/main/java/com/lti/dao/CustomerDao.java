package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Customer;

public class CustomerDao {

	public void store(Customer customer) {
		// Create EntityManagerFactory object
		// During this step, META-INF/persistence.xml file will be read
		// In double quotes- persistence-unit name
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		// Create EntityManager object
		// similar to creating statement in jdbc
		EntityManager em = emf.createEntityManager();
		// start/participate in a transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// now we can perform any sql operation
		em.persist(customer);// persist method generates INSERT query
		tx.commit();

		// should be in finally block actually
		em.close();
		emf.close();
	}
	
	public void update(Customer customer) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.merge(customer);// merge method generates UPDATE query
		tx.commit();

		em.close();
		emf.close();
	}

	public Customer fetch(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		// find method generates SELECT .. where pk=?
		Customer cust = em.find(Customer.class, id);

		em.close();
		emf.close();

		return cust;
	}

	public List<Customer> fetchAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		// JPQL-Java Persistence Query Language
		Query q = em.createQuery("select c from Customer as c");
		List<Customer> list = q.getResultList();

		em.close();
		emf.close();

		return list;
	}

	public List<Customer> fetchByEmailOf(String domainName){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		// JPQL-Java Persistence Query Language
		//SQL-> select * from tbl_cust where email_addr like '%gmail%';
		//: followed by something is a placeholder
		Query q = em.createQuery("select c from Customer as c where c.email like :em");
		q.setParameter("em", "%"+domainName+"%");
		List<Customer> list = q.getResultList();

		em.close();
		emf.close();

		return list;
	}
	
	public List<Customer> fetchByBirthYear(int year){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		// JPQL-Java Persistence Query Language
		//in sql: select * from tbl_cust where extract(year from date_of_birth)=1999;
		
		//: followed by something is a placeholder
		Query q = em.createQuery("select c from Customer as c where year(c.dateOfBirth) = :dob");
		q.setParameter("dob", year);
		List<Customer> list = q.getResultList();

		em.close();
		emf.close();

		return list;
	}
	
	
}
