package com.ibm.oss.transfer.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.oss.transfer.model.Transfer;

public interface TransferRepository extends MongoRepository<Transfer, String> {

	public List<Transfer> findByRecipient(String recipient);

	public List<Transfer> findBySender(String sender);

	public Optional<Transfer> findById(String id);

}
