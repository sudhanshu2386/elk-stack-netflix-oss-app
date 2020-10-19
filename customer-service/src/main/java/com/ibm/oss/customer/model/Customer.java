package com.ibm.oss.customer.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ibm.oss.customer.contract.Account;

@Document(collection = "Customer")
public class Customer {

	@Id
	private String id;
	private String pancardNumber;
	private String name;
	private CustomerType type;	
	private List<Account> accounts;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", pancardNumber=" + pancardNumber + ", name=" + name + ", type=" + type
				+ ", accounts=" + accounts + "]";
	}
}
