package com.lyhour.java.developer.phoneshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.developer.phoneshop.dto.ProductReportDTO;
import com.lyhour.java.developer.phoneshop.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
public class ReportController {
	private final ReportService reportService;
	
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> getSaleReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate){
		List<ProductReportDTO> report = reportService.getReport(startDate, endDate);
		
		return ResponseEntity.ok(report);
	}
	@GetMapping("/expense/{startDate}/{endDate}")
	public ResponseEntity<?> getExpenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate){
		
		List<ProductReportDTO> expenseReport = reportService.getExpenseReport(startDate, endDate);
		return ResponseEntity.ok(expenseReport);
	}
	
}
