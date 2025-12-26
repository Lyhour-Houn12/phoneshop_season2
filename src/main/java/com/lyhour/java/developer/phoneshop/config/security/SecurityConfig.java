package com.lyhour.java.developer.phoneshop.config.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final PasswordEncoder encoder;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/index.html/**", "/css/**", "/js/**").permitAll()
				.requestMatchers("/brands","/brands/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				);
		http.httpBasic(Customizer.withDefaults());
		
		
		return http.build();
		
	}
	@Bean
	public UserDetailsService userDetailsService() {
		User user1 = new User("Lyhour", encoder.encode("Lyhour123"), Collections.emptyList());
		UserDetails user2 = User.builder()
			.username("Jing")
			.password(encoder.encode("Jing123"))
			.roles("ADMIN")
			.build();
		
		
		
		UserDetailsService detailsService = new InMemoryUserDetailsManager(user1, user2);
		return detailsService;
	}
}
