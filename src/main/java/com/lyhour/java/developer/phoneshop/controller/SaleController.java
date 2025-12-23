package com.lyhour.java.developer.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.developer.phoneshop.dto.SaleDTO;
import com.lyhour.java.developer.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("sales")
public class SaleController {
	private final SaleService saleService;
	
	@PostMapping
	public ResponseEntity<?> sale(@RequestBody SaleDTO saleDTO){
		String message = "Selling product successfully.";
		saleService.saleProduct(saleDTO);
		return ResponseEntity.ok(message);
	}
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		String message = "Canceling Sale product successfully.";
		saleService.cancelSale(saleId);
		return ResponseEntity.ok(message);
	}
	
}
