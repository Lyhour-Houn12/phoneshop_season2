package com.lyhour.java.developer.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lyhour.java.developer.phoneshop.dto.ProductDTO;
import com.lyhour.java.developer.phoneshop.dto.ProductImportDTO;
import com.lyhour.java.developer.phoneshop.dto.ResponseProduct;
import com.lyhour.java.developer.phoneshop.entity.Product;
import com.lyhour.java.developer.phoneshop.entity.ProductImport;
import com.lyhour.java.developer.phoneshop.service.ColorService;
import com.lyhour.java.developer.phoneshop.service.ModelService;
import com.lyhour.java.developer.phoneshop.service.ProductService;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	Product toProduct(ProductDTO productDTO);
	
	ResponseProduct toResponseProduct(Product product);
	
	@Mapping(target = "product", source = "product")
	@Mapping(target = "id", ignore = true)
	ProductImport toProductImport(ProductImportDTO importDTO, Product product);
}
