package com.lyhour.java.developer.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.developer.phoneshop.dto.BrandDTO;
import com.lyhour.java.developer.phoneshop.entity.Brand;
import com.lyhour.java.developer.phoneshop.mapper.BrandMapper;
import com.lyhour.java.developer.phoneshop.service.BrandService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/brands")
@RestController
@RequiredArgsConstructor
public class BrandController {
	
	private final BrandService brandService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(brand);
	}
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Brand brand = brandService.getById(id);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}
	
}
