package com.lyhour.java.developer.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyhour.java.developer.phoneshop.entity.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long>{
	List<SaleDetails> findBySaleId(Long saleId);

}
