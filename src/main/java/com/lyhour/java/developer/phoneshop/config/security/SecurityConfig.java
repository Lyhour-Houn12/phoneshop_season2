package com.lyhour.java.developer.phoneshop.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.lyhour.java.developer.phoneshop.config.security.jwt.LoginFilterJwt;
import com.lyhour.java.developer.phoneshop.config.security.jwt.VerifyToken;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final PasswordEncoder encoder;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		AuthenticationManager authenticationManager = authenticationManagerBuilder.getOrBuild(); // to get authentication manager
		http.csrf(csrf -> csrf.disable());
		http.addFilter(new LoginFilterJwt(authenticationManager));
		http.addFilterAfter(new VerifyToken(), LoginFilterJwt.class);
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/index.html/**", "/css/**", "/js/**").permitAll()
				//.requestMatchers("/brands","/brands/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/brands/**").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
				.requestMatchers(HttpMethod.GET, "/brands/**").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
				.requestMatchers(HttpMethod.POST, "/models/**").hasAuthority(PermissionEnum.MODEL_WRITE.getDescription())
				.anyRequest().authenticated()
				);
		//http.httpBasic(Customizer.withDefaults());
		
		
		return http.build();
		
	}
	@Bean
	public UserDetailsService userDetailsService() {
		//User user1 = new User("Lyhour", encoder.encode("Lyhour123"), Collections.emptyList());
		UserDetails user1 = User.builder()
			.username("Lyhour")
			.password(encoder.encode("lyhour123"))
			.authorities(RoleEnum.SALE.getAuthorities())
			.build();
		
		UserDetails user2 = User.builder()
			.username("Jing")
			.password(encoder.encode("Jing123"))
			//.roles("ADMIN")
			.authorities(RoleEnum.ADMIN.getAuthorities())
			.build();
		
		
		
		UserDetailsService detailsService = new InMemoryUserDetailsManager(user1, user2);
		return detailsService;
	}
}
