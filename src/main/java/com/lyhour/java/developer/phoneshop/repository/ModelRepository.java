package com.lyhour.java.developer.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.java.developer.phoneshop.entity.Model;
@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{

}
