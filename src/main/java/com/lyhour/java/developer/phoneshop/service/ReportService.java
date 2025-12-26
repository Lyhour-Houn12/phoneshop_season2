package com.lyhour.java.developer.phoneshop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.dto.ProductReportDTO;

@Service
public interface ReportService {
	List<ProductReportDTO> getReport(LocalDate startDate, LocalDate endDate);
	List<ProductReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate);
}
