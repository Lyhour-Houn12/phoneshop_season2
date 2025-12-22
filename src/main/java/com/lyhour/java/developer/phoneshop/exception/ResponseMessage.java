package com.lyhour.java.developer.phoneshop.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ResponseMessage {
	private HttpStatus status;
	private String message;
}
