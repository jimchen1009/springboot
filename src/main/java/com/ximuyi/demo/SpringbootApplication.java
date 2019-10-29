package com.ximuyi.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.File;

/***
 * @ComponentScan("com.ximuyi.demo")
 * @EnableJpaRepositories("com.ximuyi.demo.jpa")
 * @EntityScan("com.ximuyi.demo.jpa")
 * 注释掉上面的代码后，运行报错。把类移到顶级目录就可以：
 * com.ximuyi.demo.springboot --->>> com.ximuyi.demo
 *
 *
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 *
 * @Configuration tags the class as a source of bean definitions for the application context.
 * @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
 * @ComponentScan tells Spring to look for other components, configurations, and services in the hello package, allowing it to find the controllers.
 */
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringbootApplication.class);

	public static void main(String[] args) {
		/**
		 * 启动参数的working directory
		 * $MODULE_DIR$：D:\demo\springboot\.idea\modules
		 * %MODULE_WORKING_DIR%:D:\demo\springboot
		 */
		File file = new File("");
		logger.debug("file path: {}", file.getAbsolutePath());
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.debug("SpringBoot 启动完毕!");
	}
}
