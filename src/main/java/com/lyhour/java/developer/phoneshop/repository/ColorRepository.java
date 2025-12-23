package com.lyhour.java.developer.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.java.developer.phoneshop.entity.Color;
@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
	boolean existsByName(String name);
}
