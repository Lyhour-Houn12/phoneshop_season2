package com.lyhour.java.developer.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyhour.java.developer.phoneshop.dto.PriceDTO;
import com.lyhour.java.developer.phoneshop.dto.ProductDTO;
import com.lyhour.java.developer.phoneshop.dto.ProductImportDTO;
import com.lyhour.java.developer.phoneshop.entity.Product;
import com.lyhour.java.developer.phoneshop.mapper.ProductMapper;
import com.lyhour.java.developer.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final ProductMapper productMapper;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
		Product product = productMapper.toProduct(productDTO);
		product = productService.create(product);
		return ResponseEntity.ok(productMapper.toResponseProduct(product));
	}
	@PostMapping("/importProducts")
	public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO importDTO){
		String message = "Importing Product Successfully.";
		productService.productImport(importDTO);
		return ResponseEntity.ok(message);
	}
	@PostMapping("{productId}/setSalePrice")
	public ResponseEntity<?> setSale(@PathVariable Long productId, @RequestBody PriceDTO setPrice){
		String message = "Setting Price Successfully.";
		productService.setSalePrice(productId, setPrice.getSetPrice());
		return ResponseEntity.ok(message);
	}
	@PostMapping("uploadProduct")
	public ResponseEntity<?> importByExcel(@RequestParam MultipartFile file){
		String message = "Importing Product Successfully.";
		productService.importProductByExcel(file);
		return ResponseEntity.ok(message);
	}
	
}
