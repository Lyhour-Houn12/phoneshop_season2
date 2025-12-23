package com.lyhour.java.developer.phoneshop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.dto.ProductSold;
import com.lyhour.java.developer.phoneshop.dto.SaleDTO;
import com.lyhour.java.developer.phoneshop.entity.Product;
import com.lyhour.java.developer.phoneshop.entity.Sale;
import com.lyhour.java.developer.phoneshop.entity.SaleDetails;
import com.lyhour.java.developer.phoneshop.exception.ApiException;
import com.lyhour.java.developer.phoneshop.exception.ResourceFoundOrNot;
import com.lyhour.java.developer.phoneshop.repository.ProductRepository;
import com.lyhour.java.developer.phoneshop.repository.SaleDetailsRepository;
import com.lyhour.java.developer.phoneshop.repository.SaleRepository;
import com.lyhour.java.developer.phoneshop.service.ProductService;
import com.lyhour.java.developer.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
	private final SaleRepository saleRepository;
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleDetailsRepository detailsRepository;

	@Override
	public void saleProduct(SaleDTO saleDTO) {
		// validate product
		List<Long> productIds = saleDTO.getProductSold().stream().map(ProductSold::getProductId).toList();
		productIds.forEach(productService::getById);
		List<Product> products = productRepository.findAllById(productIds);  // to get all product from id
		Map<Long, Product> productMap = products.stream()  // convert it to map
				.collect(Collectors.toMap(Product::getId, Function.identity()));
		// validate stock
		saleDTO.getProductSold().stream().forEach(ps -> {
			Product product = productMap.get(ps.getProductId());
			if (product.getUnit() < ps.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST,
						String.format("Product [%s] is not enough in stock", product.getName()));
			}
		});
		// save sale
		Sale sale = new Sale();
		sale.setDateTime(saleDTO.getDateTime());
		saleRepository.save(sale);
		// save sale details
		saleDTO.getProductSold().stream()
			.forEach(ps -> {
				Product product = productMap.get(ps.getProductId());
				SaleDetails saleDetails = new SaleDetails();
				saleDetails.setAmount(product.getPricePerUnit());
				saleDetails.setProduct(product);
				saleDetails.setSale(sale);
				saleDetails.setNumberOfUnit(ps.getNumberOfUnit());
				detailsRepository.save(saleDetails);
				// cut stock
				Integer availableUnit = product.getUnit() - ps.getNumberOfUnit();
				product.setUnit(availableUnit);
				productRepository.save(product);
			});
		
	}
	
	@Override
	public void cancelSale(Long saleId) {
		// update sale status
		Sale sale = getSaleById(saleId);
		sale.setIsActive(false);
		saleRepository.save(sale);
		// update stock product
		List<SaleDetails> saleDetials = detailsRepository.findBySaleId(saleId);
		List<Long> productIds = saleDetials.stream()  // to get id from product and convert it to map
			.map(sd -> sd.getProduct().getId())
			.toList();
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		saleDetials.forEach(ps ->{
			Product product = productMap.get(ps.getProduct().getId());
			product.setUnit(product.getUnit() + ps.getNumberOfUnit());
			productRepository.save(product);
		});
	}

	@Override
	public Sale getSaleById(Long saleId) {
		return saleRepository.findById(saleId)
			.orElseThrow(() -> new ResourceFoundOrNot("Sale", saleId));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	 * private void validate(SaleDTO saleDTO) { // validate product List<Long>
	 * productIds =
	 * saleDTO.getProductSold().stream().map(ProductSold::getProductId).toList();
	 * productIds.forEach(productService::getById); List<Product> products =
	 * productRepository.findAllById(productIds); Map<Long, Product> productMap =
	 * products.stream() .collect(Collectors.toMap(Product::getId,
	 * Function.identity())); // validate stock
	 * saleDTO.getProductSold().stream().forEach(ps -> { Product product =
	 * productMap.get(ps.getProductId()); if (product.getUnit() <
	 * ps.getNumberOfUnit()) { throw new ApiException(HttpStatus.BAD_REQUEST,
	 * String.format("Product [%s] is not enough in stock", product.getName())); }
	 * }); }
	 */

}
