package com.lyhour.java.developer.phoneshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.developer.phoneshop.dto.ColorDTO;
import com.lyhour.java.developer.phoneshop.entity.Color;
import com.lyhour.java.developer.phoneshop.mapper.ColorMapper;
import com.lyhour.java.developer.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("colors")
@RequiredArgsConstructor
public class ColorController {
	private final ColorService colorService;


    
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ColorDTO colorDTO){
		Color color = ColorMapper.INSTANCE.toColor(colorDTO);
		color = colorService.create(color);
		return ResponseEntity.ok(color);
	}
	@GetMapping
	public ResponseEntity<?> getColors(){
		List<Color> colors = colorService.getColors();
		return ResponseEntity.ok(colors);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		String message = String.format("Delete color with id = %d successfully.", id);
		colorService.deleteById(id);
		return ResponseEntity.ok(message);
	}
}
