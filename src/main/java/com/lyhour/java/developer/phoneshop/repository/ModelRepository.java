package com.lyhour.java.developer.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lyhour.java.developer.phoneshop.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model>{
	boolean existsByname(String name);
	List<Model> findByBrandId(Long id);

}
