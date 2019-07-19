package com.ximuyi.demo.cache;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
	private BookISBN isbn;
	private String title;

	public Book() {
	}

	public Book(BookISBN isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}

	public BookISBN getIsbn() {
		return isbn;
	}

	public void setIsbn(BookISBN isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return Objects.equals(isbn, book.isbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public String toString() {
		return "{" +
				"isbn='" + isbn + '\'' +
				", title='" + title + '\'' +
				'}';
	}
}
