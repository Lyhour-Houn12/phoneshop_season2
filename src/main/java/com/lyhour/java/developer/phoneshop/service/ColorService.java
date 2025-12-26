package com.lyhour.java.developer.phoneshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Color;

@Service
public interface ColorService {
	Color create(Color color);
	List<Color> getColors();
	void deleteById(Long id);
	Color getById(Long id);
	
	
	
	
}
