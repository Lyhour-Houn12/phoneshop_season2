package com.lyhour.java.developer.phoneshop.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class SaleDTO {
	private List<ProductSold> productSold;
	private LocalDateTime dateTime;

}
