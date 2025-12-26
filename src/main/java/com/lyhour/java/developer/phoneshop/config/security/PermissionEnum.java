package com.lyhour.java.developer.phoneshop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {
	BRAND_READ("brand:read"),
	BRAND_WRITE("brand:write"),
	MODEL_READ("model:read"),
	MODEL_WRITE("model:write");
	
	private String description;
}
