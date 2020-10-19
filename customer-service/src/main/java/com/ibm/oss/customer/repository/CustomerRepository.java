package com.ibm.oss.customer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.oss.customer.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByPancardNumber(String pancardNumber);

	public Optional<Customer> findById(String id);
}
