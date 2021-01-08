package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entitynew.Department;
import com.lti.entitynew.EmployeeNew;

public class EmployeeDepartmentDao extends GenericDao {

	public List<EmployeeNew> fetchByLocation(String location) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			// SQL
			// select e.* from tbl_employee e join
			// tbl_department d on d.deptno=e.dept_no
			// where d.location = 'Mumbai';
			Query q = em.createQuery("select e from EmployeeNew e join e.department d where d.location = :loc");
			q.setParameter("loc", location);
			List<EmployeeNew> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}
	}

	public List<Department> fetchDepartmentBySalary(double salary) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			// SQL
			// select d.* from tbl_department d join
			// tbl_employee e on e.dept_no=d.deptno
			// where e.salary>=40000;
			Query q = em.createQuery("select d from Department d join d.employees e where e.salary = :var");
			q.setParameter("var", salary);
			List<Department> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}
	}

	public List<Department> fetchDepartmentByNoOfEmployees(int num) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-demo");
			em = emf.createEntityManager();

			// SQL
			// select d.name from tbl_department d join
			// tbl_employee e on e.dept_no=d.deptno
			// group by d.name having count(e.name)>=2;
			Query q = em.createQuery("select d from Department d where d.employees.size >= :var");
			q.setParameter("var", num);
			List<Department> list = q.getResultList();
			return list;

		} finally {
			em.close();
			emf.close();
		}
	}
}
