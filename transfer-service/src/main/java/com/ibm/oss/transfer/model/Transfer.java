package com.ibm.oss.transfer.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Transfer")
public class Transfer {

	@Id
	private String id;
	private String sender;
	private String recipient;
	private String accountId;
	private String customerId;
	private int amount;
	private LocalDateTime createAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", sender=" + sender + ", recipient=" + recipient + ", accountId=" + accountId
				+ ", customerId=" + customerId + ", amount=" + amount + ", createAt=" + createAt + "]";
	}
}
