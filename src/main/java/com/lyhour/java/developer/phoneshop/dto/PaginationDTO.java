package com.lyhour.java.developer.phoneshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
	private int PageSize;
	private int PageNumber;
	private Long totalElements;
	private int totalPages;
	private int numberOfElement;
	private boolean first;
	private boolean last;
	private boolean empty;
}
