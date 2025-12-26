package com.lyhour.java.developer.phoneshop.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.developer.phoneshop.dto.ModelDTO;
import com.lyhour.java.developer.phoneshop.dto.PageDTO;
import com.lyhour.java.developer.phoneshop.entity.Model;
import com.lyhour.java.developer.phoneshop.mapper.ModelMapper;
import com.lyhour.java.developer.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor
public class ModelController {
	private final ModelService modelService;
	private final ModelMapper modelMapper;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
		Model model = modelMapper.toModel(modelDTO);
		model = modelService.ceate(model);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Model model = modelService.getById(id);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}
	@PutMapping("{id}")
	public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody ModelDTO modelDTO){
		Model model = modelMapper.toModel(modelDTO);
		model = modelService.updateById(id, model);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		String message = String.format("Delete model with id = %d successfully.", id);
		modelService.deleteById(id);
		return ResponseEntity.ok(message);
	}
	@GetMapping
	public ResponseEntity<?> getModels(@RequestParam Map<String , String> params){
		Page<Model> pages = modelService.getModels(params);
		Page<ModelDTO> pageDtos = pages
				.map(modelMapper::toModelDTO);
		PageDTO dto = new PageDTO(pageDtos);
		return ResponseEntity.ok(dto);
	}
	
}
