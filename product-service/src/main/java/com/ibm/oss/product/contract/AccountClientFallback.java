package com.ibm.oss.product.contract;

import org.springframework.stereotype.Component;

@Component
public class AccountClientFallback implements AccountClient {

	@Override
	public Account getAccount(String accountId) {
		return new Account();
	}

}
