package com.lyhour.java.developer.phoneshop.entity;

import java.math.BigDecimal;

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
@Table(name = "sale_details")
	public class SaleDetails {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@ManyToOne
		@JoinColumn(name = "sale_id")
		private Sale sale;
		@ManyToOne
		@JoinColumn(name = "product_id")
		private Product product;
		@Column(name = "sold_amount")
		private BigDecimal amount;
		@Column(name = "numberOfunit")
		private Integer numberOfUnit;
		
		
	}
