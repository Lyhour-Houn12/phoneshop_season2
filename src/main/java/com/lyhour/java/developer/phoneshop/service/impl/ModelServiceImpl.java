package com.lyhour.java.developer.phoneshop.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.java.developer.phoneshop.entity.Model;
import com.lyhour.java.developer.phoneshop.exception.ApiException;
import com.lyhour.java.developer.phoneshop.exception.ResourceFoundOrNot;
import com.lyhour.java.developer.phoneshop.repository.ModelRepository;
import com.lyhour.java.developer.phoneshop.service.ModelService;
import com.lyhour.java.developer.phoneshop.spec.ModelFilter;
import com.lyhour.java.developer.phoneshop.spec.ModelSpec;
import com.lyhour.java.developer.phoneshop.util.PageUtil;
@Service
public class ModelServiceImpl implements ModelService{
	@Autowired
	private ModelRepository modelRepository;
	@Override
	public Model ceate(Model model) {
		if(!modelRepository.existsByname(model.getName())) {			
			return modelRepository.save(model);
		}
		throw new ApiException(HttpStatus.BAD_REQUEST, String.format("Error: Model with name = %s already existed", model.getName()));
	}
	@Override
	public Model getById(Long id) {
		return modelRepository.findById(id)
			.orElseThrow(() -> new ResourceFoundOrNot("Model", id));
	}
	@Override
	public Model updateById(Long id, Model modelToUpdate) {
		Model model = getById(id);
		model.setName(modelToUpdate.getName());
		return model;
	}
	@Override
	public void deleteById(Long id) {
		Model model = getById(id);
		modelRepository.delete(model);
	}
	@Override
	public Page<Model> getModels(Map<String, String> params) {
		ModelFilter filter = new ModelFilter();
		if(params.containsKey("name")) {
			String name = params.get("name");
			filter.setName(name);
		}
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber =Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		int pageSize = PageUtil.DEFUALT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageSize =Integer.parseInt(params.get(PageUtil.PAGE_LIMIT)); 
		}
		ModelSpec modelSpec = new ModelSpec(filter);
		Pageable pageable = PageUtil.pageable(pageNumber, pageSize);
		return modelRepository.findAll(modelSpec, pageable);
	}

}
