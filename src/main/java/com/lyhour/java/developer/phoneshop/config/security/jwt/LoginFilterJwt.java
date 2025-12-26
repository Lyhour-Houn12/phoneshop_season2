package com.lyhour.java.developer.phoneshop.config.security.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginFilterJwt extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		}  catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String secretKey = "MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=";

		
		String token = Jwts.builder()
			.subject(authResult.getName())
			.claim("Authorities", authResult.getAuthorities())
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
			.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
			.issuer("phoneshop.com")
			
			.compact();
		
		response.setHeader("Authorization","Bearer " + token);
	}
	
	
	
	
}
