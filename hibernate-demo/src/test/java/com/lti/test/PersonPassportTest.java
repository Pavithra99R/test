package com.lti.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.lti.dao.GenericDao;
import com.lti.dao.PersonPassportDao;
import com.lti.entity.Employee;
import com.lti.entity.Passport;
import com.lti.entity.Person;

public class PersonPassportTest {

	@Test
	public void addPerson() {
		Person person = new Person();
		person.setName("Pavi");
		person.setEmail("pavi@outlook.com");
		GenericDao dao = new GenericDao();
		dao.save(person);
	}
	
	@Test
	public void issuingPassportToAPerson() {
		
		Passport pass = new Passport();
		GenericDao dao = new GenericDao();
		Person p = (Person) dao.fetch(Person.class, 61);
		pass.setIssueDate(LocalDate.of(2020, 10, 2));
		pass.setExpiryDate(LocalDate.of(2028, 10, 2));
		pass.setAuthority("india");
		pass.setPerson(p);
		dao.save(p);
		dao.save(pass);
		
	}
	
	@Test
	public void addPersonAlongWithPassport() {
		
		GenericDao dao = new GenericDao();
		Person person = new Person();
		person.setName("Subbu");
		person.setEmail("subbu@yahoo.com");
		
		Passport pass = new Passport();
		pass.setIssueDate(LocalDate.of(1999, 11, 20));
		pass.setExpiryDate(LocalDate.of(2017, 9, 22));
		pass.setAuthority("India");
		
		person.setPassport(pass);//since bidirectional
		pass.setPerson(person);
		dao.save(person); //save the class which is cascaded(here person class)
	}
	
	@Test
	public void expiredPassports() {
		PersonPassportDao dao = new PersonPassportDao();
		LocalDate date = LocalDate.of(2020, 12, 23);
		List<Person> p = dao.fetchByExpiryDate(date);
		for (Person e : p ) {
			System.out.println(e.getId() + ", " + e.getName() + ", " + e.getEmail());
		}
	}
	@Test
	public void getPassportDetails() {
		PersonPassportDao dao = new PersonPassportDao();
		List<Passport> p = dao.fetchPassportDetailsById(69);
		for (Passport e : p ) {
			System.out.println(e.getPassportNo() + ", " + e.getIssueDate() + ", " +e.getExpiryDate()+", "+ e.getAuthority());
		}
	}
	
	
}
