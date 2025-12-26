package com.lyhour.java.developer.phoneshop.spec;

import java.time.LocalDate;

import lombok.Data;
@Data
public class ProductDetailFilter {
	private LocalDate startDate;
	private LocalDate endDate;
}
