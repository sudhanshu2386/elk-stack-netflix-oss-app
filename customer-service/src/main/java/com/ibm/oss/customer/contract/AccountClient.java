package com.ibm.oss.customer.contract;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service", url = "http://account-service/act-v1", fallback = AccountClientFallback.class)
public interface AccountClient {

	@RequestMapping(method = RequestMethod.GET, value = "/act-v1/accounts/customer/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<Account> getAccounts(@PathVariable("customerId") String customerId);
}
