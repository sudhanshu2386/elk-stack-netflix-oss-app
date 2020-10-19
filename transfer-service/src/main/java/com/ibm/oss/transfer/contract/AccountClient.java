package com.ibm.oss.transfer.contract;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service", url = "http://account-service/act-v1", fallback = AccountClientFallback.class)
public interface AccountClient {

	@RequestMapping(method = RequestMethod.GET, value = "/act-v1/accounts/{accountId}")
	Account getAccount(@PathVariable("accountId") String accountId);

}
