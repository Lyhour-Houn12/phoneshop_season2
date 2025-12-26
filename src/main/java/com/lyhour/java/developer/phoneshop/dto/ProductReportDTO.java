package com.lyhour.java.developer.phoneshop.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProductReportDTO {
	private Long productId;
	private String productName;
	private Integer totalUnit;
	private BigDecimal totalPrice;
}
