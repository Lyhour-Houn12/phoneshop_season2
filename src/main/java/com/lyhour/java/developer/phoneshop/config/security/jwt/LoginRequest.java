package com.lyhour.java.developer.phoneshop.config.security.jwt;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
}
