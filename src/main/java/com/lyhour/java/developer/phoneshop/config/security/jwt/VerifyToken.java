package com.lyhour.java.developer.phoneshop.config.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerifyToken extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authorizationHeader.replace("Bearer ", "");
		String secretKey = "MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=";
		
		Claims body = Jwts.parser()
			.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
			.build()
			.parseClaimsJws(token)
			.getBody();
		String username = body.getSubject();
		List<Map<String, String>> autority = (List<Map<String, String>>) body.get("Authorities");
		Set<SimpleGrantedAuthority> authorities = autority.stream()
			.map(auth -> new SimpleGrantedAuthority(auth.get("authority")))
			.collect(Collectors.toSet());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		
	}

}
