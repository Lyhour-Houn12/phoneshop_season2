package com.lyhour.java.developer.phoneshop.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lyhour.java.developer.phoneshop.dto.ProductImportDTO;
import com.lyhour.java.developer.phoneshop.entity.Product;

@Service
public interface ProductService {
	Product create(Product product);
	Product getById(Long id);
	void productImport(ProductImportDTO importDTO);
	void setSalePrice(Long productId, BigDecimal setPrice);
	void importProductByExcel(MultipartFile multipartFile);
	Product findByModelIdAndColorId(Long modelId, Long colorId);

}
