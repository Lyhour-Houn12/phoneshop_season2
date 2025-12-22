package com.lyhour.java.developer.phoneshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{
	@ExceptionHandler(exception = ApiException.class)
	public static ResponseEntity<?> exceptionHandler(ApiException e){
		ResponseMessage message = new ResponseMessage();
		message.setStatus(e.getStatus());
		message.setMessage(e.getMessage());
		return ResponseEntity.status(e.getStatus())
				.body(message);
	}
	
	
}
