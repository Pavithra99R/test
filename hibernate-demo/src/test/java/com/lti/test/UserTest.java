package com.lti.test;

import org.junit.Test;

import com.lti.dao.GenericDao;
import com.lti.entity.User;
import com.lti.entity.UserId;

public class UserTest {

	@Test
	public void addUser() {

		User user = new User();
		UserId id = new UserId();
		id.setUsername("Pavithra");
		id.setCountry("India");
		user.setId(id);
		user.setPassword("Pavi1234");
		user.setEmail("pavi@outlook.com");

		GenericDao dao = new GenericDao();
		dao.save(user);
	}

	@Test
	public void fetchUser() {

		GenericDao dao = new GenericDao();
		UserId pk = new UserId();
		pk.setUsername("Pavithra");
		pk.setCountry("India");
		User user = (User) dao.fetch(User.class, pk);
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Username: " + user.getId().getUsername());
		System.out.println("Country: " + user.getId().getCountry());
	}
}
