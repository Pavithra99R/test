package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Address;
import com.lti.entity.Customer;
import com.lti.entity.Employee;

public class EmployeeAddressDao {

	public void store(Employee employee) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(employee);// persist method generates INSERT query
		tx.commit();
		em.close();
		emf.close();
	}

	public void store(Address address) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(address);// persist method generates INSERT query
		tx.commit();
		em.close();
		emf.close();
	}

	public Employee fetch(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();
		// find method generates SELECT .. where pk=?
		Employee emp = em.find(Employee.class, id);
		em.close();
		emf.close();
		return emp;
	}

	public void update(Employee employee) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(employee);// merge method generates UPDATE query
		tx.commit();
		em.close();
		emf.close();
	}

	public List<Employee> fetchBySalary(double salary) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		// JPQL-Java Persistence Query Language
		Query q = em.createQuery("select c from Employee as c where c.salary > :sal");
		q.setParameter("sal", salary);
		List<Employee> list = q.getResultList();

		em.close();
		emf.close();

		return list;
	}

	public List<Employee> fetchByJoiningYear(int year) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		// JPQL-Java Persistence Query Language
		Query q = em.createQuery("select c from Employee as c where year(c.dateOfJoining) = :d");
		q.setParameter("d", year);
		List<Employee> list = q.getResultList();

		em.close();
		emf.close();

		return list;
	}
	
	public List<Employee> fetchByCity(String city) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		// JPQL-Java Persistence Query Language
		Query q = em.createQuery("select e from Employee e join e.address a where a.city = :ct");
		q.setParameter("ct", city);
		List<Employee> list = q.getResultList();

		em.close();
		emf.close();

		return list;
	}
	
	public List<Address> fetchByEmpId(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");
		EntityManager em = emf.createEntityManager();

		//sql
		//select a.* from tbl_addr a join tbl_emp e 
		//on(e.addr_id=a.id) where e.id=45;
		// JPQL-Java Persistence Query Language
		Query q = em.createQuery("select a from Employee e join e.address a where e.id = :id");
		q.setParameter("id", id);
		List<Address> list = q.getResultList();

		em.close();
		emf.close();

		return list;
	}

}
