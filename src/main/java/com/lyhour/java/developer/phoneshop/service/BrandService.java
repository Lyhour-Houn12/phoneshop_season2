package com.lyhour.java.developer.phoneshop.service;

import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Brand;

@Service
public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long id);
}
