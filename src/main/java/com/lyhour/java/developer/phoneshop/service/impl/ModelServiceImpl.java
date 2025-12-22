package com.lyhour.java.developer.phoneshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Model;
import com.lyhour.java.developer.phoneshop.repository.ModelRepository;
import com.lyhour.java.developer.phoneshop.service.ModelService;
@Service
public class ModelServiceImpl implements ModelService{
	@Autowired
	private ModelRepository modelRepository;
	@Override
	public Model ceate(Model model) {
		return modelRepository.save(model);
	}

}
