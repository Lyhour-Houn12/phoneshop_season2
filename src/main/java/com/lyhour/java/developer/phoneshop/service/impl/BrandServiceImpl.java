package com.lyhour.java.developer.phoneshop.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Brand;
import com.lyhour.java.developer.phoneshop.repository.BrandRepositoty;
import com.lyhour.java.developer.phoneshop.service.BrandService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
	private final BrandRepositoty brandRepositoty;
	
	@Override
	public Brand create(Brand brand) {
		if(!brandRepositoty.existsByName(brand.getName())) {
			return brandRepositoty.save(brand);
		}
		throw new RuntimeException("Brand Already Exised");
	}

	@Override
	public Brand getById(Long id) {
		Optional<Brand> findById = brandRepositoty.findById(id);
		if(findById.isPresent()) {			
			return findById.get();
		}
		throw new RuntimeException("Brand doesn't exist");
	}

}
