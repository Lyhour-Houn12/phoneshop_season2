package com.lyhour.java.developer.phoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.dto.BrandWithModelDTOs;
import com.lyhour.java.developer.phoneshop.entity.Brand;

@Service
public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long id);
	Brand updateById(Long id, Brand brandUpdte);
	void deleteById(Long id);
	BrandWithModelDTOs getBrand(Long id);
	Page<Brand> getBrands(Map<String, String> params);
}
