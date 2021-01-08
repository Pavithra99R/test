package com.lti.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lti.dao.EmployeeDepartmentDao;
import com.lti.dao.GenericDao;
import com.lti.entitynew.Department;
import com.lti.entitynew.EmployeeNew;

public class EmployeeDepartmentTest {

	@Test
	public void addDepartment() {
		Department dept = new Department();
		dept.setName("Sales");
		dept.setLocation("Chennai");

		GenericDao dao = new GenericDao();
		dao.save(dept);
	}

	@Test
	public void addEmployeeToAExistingDepartment() {

		EmployeeNew employee = new EmployeeNew();
		employee.setName("Pavi");
		employee.setDateOfJoining(LocalDate.of(2020, 8, 21));
		employee.setSalary(35000.0);

		GenericDao dao = new GenericDao();
		Department dept = (Department) dao.fetch(Department.class, 97);

		employee.setDepartment(dept);
		dao.save(dept);
		dao.save(employee);
	}

	@Test
	public void addEmployeeAlongWithDepartment() {

		Department dept = new Department();
		dept.setName("Quality");
		dept.setLocation("Bangalore");

		EmployeeNew employee = new EmployeeNew();
		employee.setName("Nivi");
		employee.setDateOfJoining(LocalDate.of(2019, 8, 12));
		employee.setSalary(25000.0);
		employee.setDepartment(dept);

		EmployeeNew employee1 = new EmployeeNew();
		employee1.setName("Mira");
		employee1.setDateOfJoining(LocalDate.of(2015, 3, 22));
		employee1.setSalary(82000.0);
		employee1.setDepartment(dept);

		GenericDao dao = new GenericDao();
		List<EmployeeNew> employees = new ArrayList<EmployeeNew>();
		employees.add(employee);
		employees.add(employee1);

		dept.setEmployees(employees);
		dao.save(dept);
	}

	@Test
	public void getEmployeeBasedOnLocation() {
		EmployeeDepartmentDao dao = new EmployeeDepartmentDao();
		List<EmployeeNew> emp = dao.fetchByLocation("Mumbai");
		for (EmployeeNew e : emp) {
			System.out.println(e.getEmpno() + ", " + e.getName() + ", " + e.getSalary() + ", " + e.getDateOfJoining()
					+ ", " + e.getDepartment().getLocation());
		}
	}

	@Test
	public void getDepartmentBasedOnSalary() {
		EmployeeDepartmentDao dao = new EmployeeDepartmentDao();
		List<Department> dept = dao.fetchDepartmentBySalary(40000);
		for (Department e : dept) {
			System.out.println(e.getDeptno() + ", " + e.getName() + ", " + e.getLocation());
		}
	}

	@Test
	public void getDepartmentBasedOnNoOfEmployee() {
		EmployeeDepartmentDao dao = new EmployeeDepartmentDao();
		List<Department> dept = dao.fetchDepartmentByNoOfEmployees(2);
		for (Department e : dept) {
			System.out.println(e.getDeptno() + ", " + e.getName() + ", " + e.getLocation());
		}
	}
}
