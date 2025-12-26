package com.lyhour.java.developer.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.java.developer.phoneshop.entity.Sale;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
