package com.ibm.oss.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		code = HttpStatus.NOT_FOUND, 
		reason = "Customer not found with the given identifier")
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -685599544595418161L;

	
}
