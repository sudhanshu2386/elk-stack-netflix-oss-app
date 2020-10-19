package com.ibm.oss.account.api;

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

import com.ibm.oss.account.model.Account;
import com.ibm.oss.account.repository.AccountRepository;

@RestController
@RequestMapping(value = "/act-v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

	@Autowired
	AccountRepository repository;

	protected Logger logger = Logger.getLogger(AccountController.class.getName());

	@RequestMapping(value = "/accounts/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
		return repository.findByNumber(number);
	}

	@RequestMapping(value = "/accounts/customer/{customer}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> findByCustomer(@PathVariable("customer") String customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		return repository.findByCustomerId(customerId);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> findAll() {
		logger.info("Account.findAll()");
		return repository.findAll();
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account add(@RequestBody Account account) {
		logger.info(String.format("Account.add(%s)", account));
		return repository.save(account);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account update(@PathVariable("id") String id, @RequestBody Account account) {
		logger.info(String.format("Account.update(%s)", account));
		account.setId(id);
		return repository.save(account);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteFeed(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
