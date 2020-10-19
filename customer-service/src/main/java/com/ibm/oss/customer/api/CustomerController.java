package com.ibm.oss.customer.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.oss.customer.contract.Account;
import com.ibm.oss.customer.contract.AccountClient;
import com.ibm.oss.customer.exception.ResourceNotFoundException;
import com.ibm.oss.customer.model.Customer;
import com.ibm.oss.customer.repository.CustomerRepository;

@RestController
@RequestMapping(value = "/cust-v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	CustomerRepository repository;

	protected Logger logger = Logger.getLogger(CustomerController.class.getName());

	@RequestMapping(value = "/customers/pancardNumber/{pancardNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer findByPancardNumber(@PathVariable("pancardNumber") String pancardNumber) {
		logger.info(String.format("Customer.findByPancardNumber(%s)", pancardNumber));
		return repository.findByPancardNumber(pancardNumber);
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAll() {
		logger.info("Customer.findAll()");
		return repository.findAll();
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer findById(@PathVariable("id") String id) {
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
		List<Account> accounts = accountClient.getAccounts(id);
		customer.setAccounts(accounts);
		return customer;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer add(@RequestBody Customer customer) {
		logger.info(String.format("Customer.add(%s)", customer));
		return repository.save(customer);
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer update(@PathVariable("id") String id, @RequestBody Customer customer) {
		logger.info(String.format("Customer.update(%s)", customer));
		customer.setId(id);
		return repository.save(customer);
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}
