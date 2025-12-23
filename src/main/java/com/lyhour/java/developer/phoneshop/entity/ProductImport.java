package com.lyhour.java.developer.phoneshop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "product_import_histories")
public class ProductImport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "import_id")
	private Long id;
	@Column(name = "import_unit")
	private Integer importUnit;
	@Column(name = "import_price")
	private BigDecimal importPrice;
	@Column(name = "import_date")
	private LocalDateTime dateTime;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
