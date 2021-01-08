package com.lti.test;

import org.junit.Test;

import com.lti.dao.GenericDao;
import com.lti.entity.Book;

public class BookTest {

	@Test
	public void addBook() {
		
		GenericDao dao = new GenericDao();
		Book book = new Book();
		book.setName("Kalikaatu Idhigasam");
		book.setAuthor("Vayiramuthu");
		book.setPublication("Bhoomi publications");
		dao.save(book);
	}
}
