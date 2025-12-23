package com.lyhour.java.developer.phoneshop.service;

import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.dto.SaleDTO;
import com.lyhour.java.developer.phoneshop.entity.Sale;

@Service
public interface SaleService {
	void saleProduct(SaleDTO saleDTO);
	Sale getSaleById(Long saleId);
	void cancelSale(Long saleId);
}
