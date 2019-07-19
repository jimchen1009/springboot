package com.ximuyi.demo.cache;

public interface BookRepository {
	Book getByIsbn(BookISBN isbn);
	Book findBook(BookISBN isbn, boolean checkWarehouse, boolean includeUsed);
	Book findBook2(BookISBN isbn, boolean checkWarehouse, boolean includeUsed);
}
