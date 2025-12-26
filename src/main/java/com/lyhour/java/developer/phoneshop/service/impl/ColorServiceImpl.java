package com.lyhour.java.developer.phoneshop.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Color;
import com.lyhour.java.developer.phoneshop.exception.ApiException;
import com.lyhour.java.developer.phoneshop.exception.ResourceFoundOrNot;
import com.lyhour.java.developer.phoneshop.repository.ColorRepository;
import com.lyhour.java.developer.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService{
	private final ColorRepository colorRepository;
	@Override
	public Color create(Color color) {
		if(!colorRepository.existsByName(color.getName())) {
			return colorRepository.save(color);
		}
		throw new ApiException(HttpStatus.BAD_REQUEST, String.format("Error: Color name = %s already existed.", color.getName()));
	}
	
	public Color getById(Long id) {
		return colorRepository.findById(id)
			.orElseThrow(() -> new ResourceFoundOrNot("Color", id));
	}
	
	@Override
	public void deleteById(Long id) {
		Color color = getById(id);
		colorRepository.delete(color);
	}
	@Override
	public List<Color> getColors() {
		return colorRepository.findAll();
	}


}
