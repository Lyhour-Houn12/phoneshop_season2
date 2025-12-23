package com.lyhour.java.developer.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lyhour.java.developer.phoneshop.dto.ColorDTO;
import com.lyhour.java.developer.phoneshop.entity.Color;

@Mapper
public interface ColorMapper {
	ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
	
	Color toColor(ColorDTO colorDTO);
}
