package com.lyhour.java.developer.phoneshop.entity;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	@Column(name = "product_name")
	private String name;
	private String image;
	@Column(name = "product_unit")
	private Integer unit;
	@Column(name = "product_price_per_unit")
	private BigDecimal pricePerUnit;
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

}
