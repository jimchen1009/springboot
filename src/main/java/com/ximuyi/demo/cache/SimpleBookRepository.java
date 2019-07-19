package com.ximuyi.demo.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/***
 *
 */
@Component
public class SimpleBookRepository implements BookRepository {

	@Cacheable(cacheNames={"books", "isbns"},key="#isbn.rawNumber")  //redis key: books::{rawNumber='isbn-1234'}
	@Override
	public Book getByIsbn(BookISBN isbn) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	@Cacheable(cacheNames="books", key="#isbn")  //redis key: books::isbn-4567
	@Override
	public Book findBook(BookISBN isbn, boolean checkWarehouse, boolean includeUsed) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	//使用hash会发生redis的键值冲突的~，key的结果需要唯一
	@Cacheable(cacheNames="books", key="T(com.ximuyi.demo.cache.BookISBN).hash(#isbn)")  //redis key: books::2023949720
	@Override
	public Book findBook2(BookISBN isbn, boolean checkWarehouse, boolean includeUsed) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	// Don't do this at home
	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
