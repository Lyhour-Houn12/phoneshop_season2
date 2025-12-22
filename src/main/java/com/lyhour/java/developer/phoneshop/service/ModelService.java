package com.lyhour.java.developer.phoneshop.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Model;

@Service
public interface ModelService {
	Model ceate(Model model);
	Model getById(Long id);
	Model updateById(Long id , Model model);
	void deleteById(Long id);
	Page<Model> getModels(Map<String, String> params);
}
