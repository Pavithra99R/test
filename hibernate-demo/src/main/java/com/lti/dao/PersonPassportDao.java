package com.lti.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Passport;
import com.lti.entity.Person;

public class PersonPassportDao extends GenericDao {

	public List<Person> fetchByExpiryDate(LocalDate date) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			// JPQL-Java Persistence Query Language
			// select p.* from tbl_person p join
			// tbl_passport t on p.id=t.person_id
			// where t.expirydate < sysdate;
			Query q = em.createQuery("select p from Person p join p.passport t where t.expiryDate <= :d");
			q.setParameter("d", date);
			List<Person> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}

	}
	
	public List<Passport> fetchPassportDetailsById(int id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			// JPQL-Java Persistence Query Language
			// select t.* from tbl_passport t join
			// tbl_person p on t.person_id = p.id where p.id=69;
			Query q = em.createQuery("select t from Passport t join t.person p where p.id = :id");
			q.setParameter("id", id);
			List<Passport> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}

	}

}
