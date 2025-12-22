package com.lyhour.java.developer.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lyhour.java.developer.phoneshop.entity.Brand;

@Repository
public interface BrandRepositoty extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand>{
	boolean existsByName(String name);
	List<Brand> findByNameContainingIgnoreCase(String name);
	//Page<Brand> findAll(Pageable pageable);
	
}
