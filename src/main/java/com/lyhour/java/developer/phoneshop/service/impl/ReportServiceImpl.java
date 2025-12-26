package com.lyhour.java.developer.phoneshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.dto.ProductReportDTO;
import com.lyhour.java.developer.phoneshop.entity.Product;
import com.lyhour.java.developer.phoneshop.entity.ProductImport;
import com.lyhour.java.developer.phoneshop.entity.SaleDetails;
import com.lyhour.java.developer.phoneshop.repository.ProductImportRepository;
import com.lyhour.java.developer.phoneshop.repository.ProductRepository;
import com.lyhour.java.developer.phoneshop.repository.SaleDetailsRepository;
import com.lyhour.java.developer.phoneshop.service.ReportService;
import com.lyhour.java.developer.phoneshop.spec.ProductDetailFilter;
import com.lyhour.java.developer.phoneshop.spec.ProductDetailSpec;
import com.lyhour.java.developer.phoneshop.spec.SaleDetailsFilter;
import com.lyhour.java.developer.phoneshop.spec.SaleDetailsSpec;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final ProductRepository productRepository;
	private final SaleDetailsRepository detailsRepository;
	private final ProductImportRepository importRepository;
	
	@Override
	public List<ProductReportDTO> getReport(LocalDate startDate, LocalDate endDate) {
		List<ProductReportDTO> reportDTOs = new ArrayList<>();
		SaleDetailsFilter detailsFilter = new SaleDetailsFilter();
		detailsFilter.setStartDate(startDate);
		detailsFilter.setEndDate(endDate);
		Specification<SaleDetails> spec = new SaleDetailsSpec(detailsFilter);
		List<SaleDetails> saleDetails = detailsRepository.findAll(spec);
		List<Long> productId = saleDetails.stream()
			.map(sd -> sd.getProduct().getId())
			.collect(Collectors.toList());
		List<Product> products = productRepository.findAllById(productId);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		Map<Long, List<SaleDetails>> saleDetailsMap = saleDetails.stream()
			.collect(Collectors.groupingBy(sd -> sd.getProduct().getId()));
		
		
		for(Map.Entry<Long, List<SaleDetails>> entry : saleDetailsMap.entrySet()) {
			Product product = productMap.get(entry.getKey());
			
			// call from sale details in order to total unit and price
			List<SaleDetails> sdList = entry.getValue();
			Integer unit = sdList.stream()
				.map(sd -> sd.getNumberOfUnit())
				.reduce(0, (a,b) -> a+b);
			double totalPrice = sdList.stream()
				.mapToDouble(sd -> sd.getNumberOfUnit() * sd.getAmount().doubleValue())
				.sum();
			
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setTotalUnit(unit);
			reportDTO.setTotalPrice(BigDecimal.valueOf(totalPrice));	
			reportDTOs.add(reportDTO);
		}		
		return reportDTOs;
	}

	@Override
	public List<ProductReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
		List<ProductReportDTO> reportDTOs = new ArrayList<>();
		ProductDetailFilter detailFilter = new ProductDetailFilter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		Specification<ProductImport> spec = new ProductDetailSpec(detailFilter);
		
		
		List<ProductImport> productImport = importRepository.findAll(spec);
		List<Long> productIds = productImport.stream()
			.map(pi -> pi.getProduct().getId())
			.collect(Collectors.toList());
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		
		Map<Long, List<ProductImport>> productImportMap = productImport.stream()
			.collect(Collectors.groupingBy(pi -> pi.getProduct().getId()));
		
		for(Map.Entry<Long, List<ProductImport>> entry : productImportMap.entrySet()) {
			Product product = productMap.get(entry.getKey());
			
			List<ProductImport> proImp = entry.getValue();
			Integer totalUnit = proImp.stream()
				.map(pi -> pi.getImportUnit())
				.reduce(0, (a,b) -> a+b);
			double totalAmount = proImp.stream()
				.mapToDouble(pi -> pi.getImportUnit() * pi.getImportPrice().doubleValue())
				.sum();
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setTotalUnit(totalUnit);
			reportDTO.setTotalPrice(BigDecimal.valueOf(totalAmount));
			
			reportDTOs.add(reportDTO);	
		}
		return reportDTOs;
	}

}
