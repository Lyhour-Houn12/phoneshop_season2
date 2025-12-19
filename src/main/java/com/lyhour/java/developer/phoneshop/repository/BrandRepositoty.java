package com.lyhour.java.developer.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.java.developer.phoneshop.entity.Brand;

@Repository
public interface BrandRepositoty extends JpaRepository<Brand, Long>{
	boolean existsByName(String name);
}
