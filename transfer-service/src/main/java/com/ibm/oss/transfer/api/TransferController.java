package com.ibm.oss.transfer.api;

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

import com.ibm.oss.transfer.contract.Account;
import com.ibm.oss.transfer.contract.AccountClient;
import com.ibm.oss.transfer.exception.ResourceNotFoundException;
import com.ibm.oss.transfer.model.Transfer;
import com.ibm.oss.transfer.respository.TransferRepository;

@RestController
@RequestMapping(value = "/tnfr-v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferController {

	@Autowired
	private AccountClient accountClient;

	@Autowired
	TransferRepository repository;

	protected Logger logger = Logger.getLogger(TransferController.class.getName());

	@RequestMapping(value = "/transfers/sender/{sender}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transfer> findBySender(@PathVariable("sender") String sender) {
		logger.info(String.format("Transfer.findBySender(%s)", sender));
		return repository.findBySender(sender);
	}

	@RequestMapping(value = "/transfers/recipient/{recipient}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transfer> findByRecipient(@PathVariable("recipient") String recipient) {
		logger.info(String.format("Transfer.findByRecipient(%s)", recipient));
		return repository.findByRecipient(recipient);
	}

	@RequestMapping(value = "/transfers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transfer> findAll() {
		logger.info("Transfer.findAll()");
		return repository.findAll();
	}

	@RequestMapping(value = "/transfers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Transfer findById(@PathVariable("id") String id) {
		logger.info(String.format("Transfer.findById(%s)", id));
		Transfer transfer = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
		Account account = accountClient.getAccount(id);
		transfer.setCustomerId(account.getCustomerId());
		return transfer;
	}

	@RequestMapping(value = "/transfers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Transfer add(@RequestBody Transfer transfer) {
		logger.info(String.format("Transfer.add(%s)", transfer));
		return repository.save(transfer);
	}

	@RequestMapping(value = "/transfers/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Transfer update(@PathVariable("id") String id, @RequestBody Transfer transfer) {
		logger.info(String.format("Transfer.update(%s)", transfer));
		transfer.setId(id);
		return repository.save(transfer);
	}

	@RequestMapping(value = "/transfers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteTransfer(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
