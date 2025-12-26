package com.lyhour.java.developer.phoneshop.config.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(PermissionEnum.BRAND_WRITE,PermissionEnum.BRAND_READ, PermissionEnum.MODEL_READ, PermissionEnum.MODEL_WRITE))
	, SALE(Set.of(PermissionEnum.BRAND_READ, PermissionEnum.MODEL_READ));
	
	private Set<PermissionEnum> permissions;
	
	public Set<SimpleGrantedAuthority> getAuthorities(){
		Set<SimpleGrantedAuthority> grantedAuthories = this.permissions.stream()
			.map(auth -> new SimpleGrantedAuthority(auth.getDescription()))
			.collect(Collectors.toSet());
		
		return grantedAuthories;
	}
	
	
}
