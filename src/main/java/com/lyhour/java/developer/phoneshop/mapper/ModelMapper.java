package com.lyhour.java.developer.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lyhour.java.developer.phoneshop.dto.ModelDTO;
import com.lyhour.java.developer.phoneshop.entity.Model;
import com.lyhour.java.developer.phoneshop.service.BrandService;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelMapper {
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO modelDTO);
	
	
	@Mapping(target = "brandId", source = "brand.id")
	ModelDTO toModelDTO(Model model);

}
