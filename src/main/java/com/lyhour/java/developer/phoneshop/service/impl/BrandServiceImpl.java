package com.lyhour.java.developer.phoneshop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Brand;
import com.lyhour.java.developer.phoneshop.exception.ApiException;
import com.lyhour.java.developer.phoneshop.exception.ResourceFoundOrNot;
import com.lyhour.java.developer.phoneshop.repository.BrandRepositoty;
import com.lyhour.java.developer.phoneshop.service.BrandService;
import com.lyhour.java.developer.phoneshop.spec.BrandFilter;
import com.lyhour.java.developer.phoneshop.spec.BrandSpec;
import com.lyhour.java.developer.phoneshop.util.PageUtil;

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
		throw new ApiException(HttpStatus.BAD_REQUEST, String.format("Error: Brand with the name: %s which you type already exist.", brand.getName()));
	}

	@Override
	public Brand getById(Long id) {
		return brandRepositoty.findById(id)
			.orElseThrow(() -> new ResourceFoundOrNot("Brand", id));
			
	}

	@Override
	public Brand updateById(Long id, Brand brandUpdte) {
		Brand brand = getById(id);
		brand.setName(brandUpdte.getName());
		return brandRepositoty.save(brand);
	}

	@Override
	public void deleteById(Long id) {
		Brand brand = getById(id);
		brandRepositoty.delete(brand);
	}

	@Override
	public List<Brand> getBrandAll(String names) {
		List<Brand> items = brandRepositoty.findByNameContainingIgnoreCase(names);
		if(items.isEmpty()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, String.format("This name = %s which in this group is not containing", names));
		}
		return items;
	}

	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);;
		}
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Long.parseLong(id));
		}
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber =Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		int pageLimit = PageUtil.DEFUALT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		Pageable pageable = PageUtil.pageable(pageNumber, pageLimit);
		
		return brandRepositoty.findAll(brandSpec, pageable);
	}

	
	
	
	 

}
