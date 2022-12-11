package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;

public class Book {

	private Long id;
	private String ISBN;
	private String title;
	private String subject;
	private String publisher;
	private String language;
	private int numberOfPages;

	public Book() {
	}

	public Book(Long id, String iSBN, String title, String subject, String publisher, String language,
			int numberOfPages) {

		this.id = id;
		ISBN = iSBN;
		this.title = title;
		this.subject = subject;
		this.publisher = publisher;
		this.language = language;
		this.numberOfPages = numberOfPages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

}
