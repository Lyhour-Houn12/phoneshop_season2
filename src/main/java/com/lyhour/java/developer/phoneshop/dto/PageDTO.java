package com.lyhour.java.developer.phoneshop.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageDTO {
	private List<?> list;
	private PaginationDTO pagination;
	
	public PageDTO(Page<?> page) {
		this.list = page.getContent();
		this.pagination = PaginationDTO.builder()
				.PageSize(page.getSize())
				.PageNumber(page.getNumber() + 1)
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.numberOfElement(page.getNumberOfElements())
				.first(page.isFirst())
				.last(page.isLast())
				.empty(page.isEmpty())
				.build();
				
	}
}
