package com.ximuyi.demo.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Iterable<Customer> findByLastName(String lastName);
}
