package com.ximuyi.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void run(String... args) throws Exception {
		//logger.info("---------------Let's inspect the beans provided by Spring Boot!---------------");
		//String[] beanNames = applicationContext.getBeanDefinitionNames();
		//Arrays.sort(beanNames);
		//for (String beanName : beanNames) {
		//	logger.info(beanName);
		//}
		//logger.info("------------------------------------------------------------------------------");
	}
}
