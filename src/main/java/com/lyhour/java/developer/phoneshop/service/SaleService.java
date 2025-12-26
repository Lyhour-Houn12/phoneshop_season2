package com.lyhour.java.developer.phoneshop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.dto.ProductReportDTO;
import com.lyhour.java.developer.phoneshop.dto.SaleDTO;
import com.lyhour.java.developer.phoneshop.entity.Sale;
import com.lyhour.java.developer.phoneshop.entity.SaleDetails;

@Service
public interface SaleService {
	void saleProduct(SaleDTO saleDTO);
	Sale getSaleById(Long saleId);
	void cancelSale(Long saleId);
	
}
