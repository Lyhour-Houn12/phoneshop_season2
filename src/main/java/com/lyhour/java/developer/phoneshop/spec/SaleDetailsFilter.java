package com.lyhour.java.developer.phoneshop.spec;

import java.time.LocalDate;

import lombok.Data;
@Data
public class SaleDetailsFilter {
	private LocalDate startDate;
	private LocalDate endDate;
}
