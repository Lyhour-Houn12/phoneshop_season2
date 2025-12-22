package com.lyhour.java.developer.phoneshop.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{
	private HttpStatus status;
	private String message;
}
