package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GenericDao {

	public Object save(Object obj) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Object updatedObj = em.merge(obj);// persist method generates INSERT query
			// merge is actually 2 in 1 method
			// can be used for insert as well as update
			// merge generates insert query for transient objects
			// generates update query for detached object
			tx.commit();
			return updatedObj;
		}

		finally {
			em.close();
			emf.close();
		}

	}

	public Object fetch(Class clazz, Object pk) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();
			// find method generates SELECT .. where pk=?
			Object obj = em.find(clazz, pk);
			return obj;
		}

		finally {
			em.close();
			emf.close();
		}

	}
	
	//public List<?> fetchAll(String entityName) {
		public List<?> fetchAll(Class clazz) {
			EntityManagerFactory emf = null;
			EntityManager em = null;
			try {
				emf = Persistence.createEntityManagerFactory("oracle-pu");
				em = emf.createEntityManager();

				//String jpql = "select obj from " + entityName + " as obj";
				String jpql = "select obj from " + clazz.getName() + " as obj";
				Query q = em.createQuery(jpql);
				List<?> list = q.getResultList();
				return list;
			}
			finally {
				em.close();
				emf.close();
			}
			
		}
	
	

}
