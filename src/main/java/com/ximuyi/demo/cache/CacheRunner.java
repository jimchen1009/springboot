package com.ximuyi.demo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CacheRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CacheRunner.class);

	private final BookRepository repository;

	public CacheRunner(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		//在配置表存在rdis的配置~ 默认的缓存实现是redis的。
		BookISBN bookISBN0 = new BookISBN("isbn-4567");
		BookISBN bookISBN1 = new BookISBN("isbn-1234");
		logger.info("{} -->{}", bookISBN0, repository.getByIsbn(bookISBN0));
		logger.info("{} -->{}", bookISBN1, repository.getByIsbn(bookISBN1));
		logger.info("{} -->{}", bookISBN0, repository.getByIsbn(bookISBN0));
		logger.info("{} -->{}", bookISBN1, repository.getByIsbn(bookISBN1));

		logger.info("{} -->{}", bookISBN0, repository.findBook(bookISBN0, true, true));
		logger.info("{} -->{}", bookISBN1, repository.findBook(bookISBN1, true, true));
		logger.info("{} -->{}", bookISBN0, repository.findBook(bookISBN0, true, true));
		logger.info("{} -->{}", bookISBN1, repository.findBook(bookISBN1, true, true));

		logger.info("{} -->{}", bookISBN0, repository.findBook2(bookISBN0, true, true));
		logger.info("{} -->{}", bookISBN1, repository.findBook2(bookISBN1, true, true));
		logger.info("{} -->{}", bookISBN0, repository.findBook2(bookISBN0, true, true));
		logger.info("{} -->{}", bookISBN1, repository.findBook2(bookISBN1, true, true));
	}
}
