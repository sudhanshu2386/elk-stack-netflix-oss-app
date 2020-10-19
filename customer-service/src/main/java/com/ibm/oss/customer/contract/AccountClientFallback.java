package com.ibm.oss.customer.contract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AccountClientFallback implements AccountClient {

	@Override
	public List<Account> getAccounts(String customerId) {
		return new ArrayList<Account>();
	}
}
