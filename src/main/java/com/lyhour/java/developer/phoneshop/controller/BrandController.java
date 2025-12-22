package com.lyhour.java.developer.phoneshop.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.developer.phoneshop.dto.BrandDTO;
import com.lyhour.java.developer.phoneshop.dto.PageDTO;
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
	@PutMapping("{id}")
	public ResponseEntity<?> updateBrandById(@PathVariable Long id, @RequestBody BrandDTO brandUpdate){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandUpdate);
		Brand update = brandService.updateById(id,brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(update));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		String messageDelete = String.format("Delete Brand with id = %d Successfully.", id);
		brandService.deleteById(id);
		return ResponseEntity.ok(messageDelete);
	}
//	@GetMapping(params = {"page", "size"})
//	public ResponseEntity<?> pageBrands(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
//		return ResponseEntity.ok(brandService.pageBrand(page, size));
//		
//	}
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		Page<Brand> pages = brandService.getBrands(params);
		PageDTO pageDTO = new PageDTO(pages);
		return ResponseEntity.ok(pageDTO);
		
	}
	
}
