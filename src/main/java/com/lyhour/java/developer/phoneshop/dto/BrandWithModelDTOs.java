package com.lyhour.java.developer.phoneshop.dto;

import java.util.List;

import lombok.Data;
@Data
public class BrandWithModelDTOs {
	private Long id;
	private String name;
	private List<ModelDTO> models;
}
