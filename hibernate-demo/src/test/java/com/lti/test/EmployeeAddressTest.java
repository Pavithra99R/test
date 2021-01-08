package com.lti.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.lti.dao.EmployeeAddressDao;
import com.lti.entity.Address;
import com.lti.entity.Employee;

public class EmployeeAddressTest {

	@Test
	public void addNewEmployee() {
		Employee emp = new Employee();
		emp.setName("Raj");
		emp.setDateOfJoining(LocalDate.of(2020, 10, 10));
		emp.setSalary(30000);

		EmployeeAddressDao dao = new EmployeeAddressDao();
		dao.store(emp);
		// add asserts which will test the code

	}

	@Test
	public void addAddressForAnExistingEmployee() {

		EmployeeAddressDao dao = new EmployeeAddressDao();
		Employee emp = dao.fetch(41);
		Address addr = new Address();
		addr.setLandmark("Avinashi Road");
		addr.setCity("Coimbatore");
		addr.setPincode(641097);
		// setting the relationship
		emp.setAddress(addr);
		dao.store(addr);
		dao.update(emp);
	}

	@Test
	public void addANewEmployeeAlongWithAddress() {

		EmployeeAddressDao dao = new EmployeeAddressDao();
		Employee emp = new Employee();
		emp.setName("Nidhi");
		emp.setDateOfJoining(LocalDate.of(2019, 10, 10));
		emp.setSalary(40000);

		Address addr = new Address();
		addr.setLandmark("Vadapalani");
		addr.setCity("Chennai");
		addr.setPincode(641002);

		emp.setAddress(addr);
		dao.store(addr);
		dao.store(emp);

	}

	@Test
	public void addANewEmployeeAlongWithAddressUsingCascade() {

		EmployeeAddressDao dao = new EmployeeAddressDao();
		Employee emp = new Employee();
		emp.setName("Srishti");
		emp.setDateOfJoining(LocalDate.of(2018, 9, 10));
		emp.setSalary(60000);

		Address addr = new Address();
		addr.setLandmark("OMR");
		addr.setCity("Chennai");
		addr.setPincode(631003);

		emp.setAddress(addr);

		// when emp is saved, address also gets saved automatically
		dao.store(emp);

	}

	@Test
	public void checkSalary() {

		EmployeeAddressDao dao = new EmployeeAddressDao();
		List<Employee> emp = dao.fetchBySalary(50000);
		for (Employee e : emp) {
			System.out.println(e.getId() + ", " + e.getName() + ", " + e.getSalary());
		}

	}

	@Test
	public void checkDateOfJoining() {

		EmployeeAddressDao dao = new EmployeeAddressDao();
		List<Employee> emp = dao.fetchByJoiningYear(2019);
		for (Employee e : emp) {
			System.out.println(e.getId() + ", " + e.getName() + ", " + e.getSalary() + ", " + e.getDateOfJoining());
		}

	}
	
	@Test
	public void checkCity() {

		EmployeeAddressDao dao = new EmployeeAddressDao();
		List<Employee> emp = dao.fetchByCity("Chennai");
		for (Employee e : emp) {
			System.out.println(e.getId() + ", " + e.getName() + ", " + e.getSalary() + ", " + e.getDateOfJoining());
		}

	}
	
	@Test
	public void checkAddress() {

		EmployeeAddressDao dao = new EmployeeAddressDao();
		List<Address> emp = dao.fetchByEmpId(45);
		for (Address e : emp) {
			System.out.println(e.getCity() + ", " + e.getLandmark() + ", " + e.getPincode());
		}

	}

}
