package com.lyhour.java.developer.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.developer.phoneshop.dto.ModelDTO;
import com.lyhour.java.developer.phoneshop.entity.Model;
import com.lyhour.java.developer.phoneshop.mapper.ModelMapper;
import com.lyhour.java.developer.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor
public class ModelController {
	private final ModelService modelService;
	private final ModelMapper modelMapper;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
		Model model = modelMapper.toModel(modelDTO);
		model = modelService.ceate(model);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}
}
