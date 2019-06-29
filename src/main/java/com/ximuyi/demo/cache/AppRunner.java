package com.ximuyi.demo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final BookRepository repository;

	public AppRunner(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		//在配置表存在rdis的配置~ 默认的缓存实现是redis的。
		logger.info("isbn-1234 -->" + repository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + repository.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + repository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + repository.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + repository.getByIsbn("isbn-1234"));
		logger.info("isbn-1234 -->" + repository.getByIsbn("isbn-1234"));
	}
}
