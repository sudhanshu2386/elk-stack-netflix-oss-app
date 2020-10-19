package com.ibm.oss.product.api;

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

import com.ibm.oss.product.contract.Account;
import com.ibm.oss.product.contract.AccountClient;
import com.ibm.oss.product.exception.ResourceNotFoundException;
import com.ibm.oss.product.model.Product;
import com.ibm.oss.product.respository.ProductRepository;

@RestController
@RequestMapping(value = "/prdct-v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	ProductRepository repository;

	protected Logger logger = Logger.getLogger(ProductController.class.getName());

	@RequestMapping(value = "/products/account/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findByPancardNumber(@PathVariable("accountId") String accountId) {
		logger.info(String.format("Product.findByAccountId(%s)", accountId));
		return repository.findByAccountId(accountId);
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> findAll() {
		logger.info("Product.findAll()");
		return repository.findAll();
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findById(@PathVariable("id") String id) {
		logger.info(String.format("Product.findById(%s)", id));
		Product product = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
		Account account = accountClient.getAccount(id);
		product.setCustomerId(account.getCustomerId());
		return product;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product add(@RequestBody Product product) {
		logger.info(String.format("Product.add(%s)", product));
		return repository.save(product);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product update(@PathVariable("id") String id, @RequestBody Product product) {
		logger.info(String.format("Product.update(%s)", product));
		product.setId(id);
		return repository.save(product);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
