package com.lyhour.java.developer.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.ProductImport;

@Service
public interface ProductImportRepository extends JpaRepository<ProductImport, Long>, JpaSpecificationExecutor<ProductImport>{

}
