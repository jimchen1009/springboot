package com.ximuyi.demo;

import com.ximuyi.demo.jpa.Customer;
import com.ximuyi.demo.jpa.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

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
@EnableCaching
public class SpringbootApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringbootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			StringBuilder builder = new StringBuilder();
			for (String beanName : beanNames) {
				builder.append("\n").append(beanName);
			}
			logger.debug("Let's inspect the beans provided by Spring Boot:{}", builder.toString());

		};
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			Iterable<Customer> customers = repository.findAll();
			for (Customer customer : customers) {
				repository.delete(customer);
			}
			repository.deleteAll();
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			logger.info("Customers found with findAll():");
			logger.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				logger.info(customer.toString());
			}
			logger.info("");

			// fetch an individual customer by ID
			repository.findById(1L).ifPresent(customer -> {
				logger.info("Customer found with findById(1L):");
				logger.info("--------------------------------");
				logger.info(customer.toString());
				logger.info(""); });

			// fetch customers by last name
			logger.info("Customer found with findByLastName('Bauer'):");
			logger.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				logger.info(bauer.toString());
			});
			logger.info("");
		};
	}
}
