package com.lyhour.java.developer.phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ResourceFoundOrNot extends ApiException{
	public ResourceFoundOrNot(String name, Long id) {
		super(HttpStatus.NOT_FOUND, String.format("Error: %s With Id = %d Not Found", name, id));
	}
	
}
