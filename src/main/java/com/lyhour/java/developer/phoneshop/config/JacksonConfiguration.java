package com.lyhour.java.developer.phoneshop.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
@Configuration
public class JacksonConfiguration {
	@Bean
	public ObjectMapper objectMapper() {
	    ObjectMapper mapper = new ObjectMapper();
	    JavaTimeModule module = new JavaTimeModule();
	    module.addDeserializer(
	        LocalDateTime.class,
	        new LocalDateTimeDeserializer(
	            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
	        )
	    );
	    mapper.registerModule(module);
	    return mapper;
	}
}
