package com.lyhour.java.developer.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lyhour.java.developer.phoneshop.dto.BrandDTO;
import com.lyhour.java.developer.phoneshop.entity.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	@Mapping(target = "id", source = "id")
	Brand toBrand(BrandDTO brandDTO);
	
	BrandDTO toBrandDTO(Brand brand);
}
