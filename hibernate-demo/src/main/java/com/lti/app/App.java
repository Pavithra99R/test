package com.lti.app;

import java.time.LocalDate;
import java.util.List;

import com.lti.dao.CustomerDao;
import com.lti.entity.Customer;

public class App {

	public static void main(String[] args) {
		Customer c = new Customer();
		c.setName("Nivi");
		c.setEmail("niv@outlook.com");
		LocalDate date = LocalDate.of(1999, 1, 24);
		c.setDateOfBirth(date);
	
		CustomerDao dao = new CustomerDao();
		dao.store(c);
		
//		CustomerDao dao = new CustomerDao();
//		Customer c = dao.fetch(1);
//		System.out.println(c.getId()+", "+c.getName()+", "+c.getEmail());
		
//		CustomerDao dao = new CustomerDao();
//		List<Customer> list = dao.fetchAll();
//		for(Customer c: list) {
//			System.out.println(c.getId()+", "+c.getName()+", "+c.getEmail()+", "+c.getDateOfBirth());
//		}
		
//		CustomerDao dao = new CustomerDao();
//		List<Customer> list = dao.fetchByEmailOf("gmail");
//		for(Customer c: list) {
//			System.out.println(c.getId()+", "+c.getName()+", "+c.getEmail()+", "+c.getDateOfBirth());
//		}
		
//		CustomerDao dao = new CustomerDao();
//		List<Customer> list = dao.fetchByBirthYear(1999);
//		for(Customer c: list) {
//			System.out.println(c.getId()+", "+c.getName()+", "+c.getEmail()+", "+c.getDateOfBirth());
//		}

		//UPDATE
//		CustomerDao dao = new CustomerDao();
//		Customer c = dao.fetch(1);
//		c.setEmail("pavi@yahoo.com");
//		dao.update(c);
		
	}

}
