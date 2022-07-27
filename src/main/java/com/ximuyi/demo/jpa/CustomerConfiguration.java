package com.ximuyi.demo.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CustomerConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(CustomerConfiguration.class);

	@Bean("customerRunner")
	public CommandLineRunner commandLineRunner(CustomerRepository repository) {
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
