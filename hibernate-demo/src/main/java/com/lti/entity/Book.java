package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/* Before this, for generating sequence, run this code in Sql developer
 * CREATE SEQUENCE book_seq 
	START WITH 100001 INCREMENT BY 1 NOCACHE NOCYCLE;
 * */
@Entity
@Table(name = "tbl_book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_custom_book_seq")
	@SequenceGenerator(sequenceName = "book_seq", allocationSize = 1, name = "my_custom_book_seq")
	private long isbn;

	private String name;
	private String author;
	private String publication;

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

}
