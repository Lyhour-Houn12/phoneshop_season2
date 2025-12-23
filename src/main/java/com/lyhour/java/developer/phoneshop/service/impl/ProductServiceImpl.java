package com.lyhour.java.developer.phoneshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lyhour.java.developer.phoneshop.dto.ProductImportDTO;
import com.lyhour.java.developer.phoneshop.entity.Product;
import com.lyhour.java.developer.phoneshop.entity.ProductImport;
import com.lyhour.java.developer.phoneshop.exception.ApiException;
import com.lyhour.java.developer.phoneshop.exception.ResourceFoundOrNot;
import com.lyhour.java.developer.phoneshop.mapper.ProductMapper;
import com.lyhour.java.developer.phoneshop.repository.ProductImportRepository;
import com.lyhour.java.developer.phoneshop.repository.ProductRepository;
import com.lyhour.java.developer.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	private final ProductImportRepository importRepository;
	private final ProductMapper mapper;
	@Override
	public Product create(Product product) {
		String nameProduct = product.getModel().getName()+ " "+ product.getColor().getName();
		product.setName(nameProduct);
		if(!productRepository.existsByName(nameProduct)) {
			return productRepository.save(product);
		}
		throw new ApiException(HttpStatus.BAD_REQUEST, String.format("Error: Product with name = %s alreay existed.", nameProduct));	
	}
	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new ResourceFoundOrNot("Product", id));
	}
	@Override
	public void productImport(ProductImportDTO importDTO) {
		if(importDTO.getImportUnit() < 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Import Unit must be greater than 0");
		}
		// update product  product
		Product product = getById(importDTO.getProductId());
		Integer importUnit = 0;
		if(product.getUnit() != null) {
			importUnit = product.getUnit();
		}
		product.setUnit(importUnit + importDTO.getImportUnit());
		productRepository.save(product);
		// save import product
		ProductImport productImport = mapper.toProductImport(importDTO, product);
		importRepository.save(productImport);
	}
	@Override
	public void setSalePrice(Long productId, BigDecimal setPrice) {
		Product product = getById(productId);
		product.setPricePerUnit(setPrice);
		productRepository.save(product);
		
	}
	@Override
	public void importProductByExcel(MultipartFile file) {
		
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheet("products");
			Iterator<Row> rowIterator = sheet.iterator();
			// through header eg. colorId, modelId
			if (rowIterator.hasNext()) rowIterator.next();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				Cell cellModelId = row.getCell(0);
				Long modelId =(long) cellModelId.getNumericCellValue();
				Cell cellColorId = row.getCell(1);
				Long colorId =(long) cellColorId.getNumericCellValue();
				Cell cellImportPrice = row.getCell(2);
				Double importPrice = cellImportPrice.getNumericCellValue();
				Cell cellImportUnit = row.getCell(3);
				Integer importUnit =(int) cellImportUnit.getNumericCellValue();
				Cell cellImportDate = row.getCell(4);
				LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();
				
				// validate and save to product
				Product product = findByModelIdAndColorId(modelId, colorId);
				Integer availableUnit = 0;
				if(product.getUnit() != null) {
					availableUnit = product.getUnit();
				}
				product.setUnit(availableUnit + importUnit);
				productRepository.save(product);
				// import product
				ProductImport productImport = new ProductImport();
				productImport.setProduct(product);
				productImport.setImportPrice(BigDecimal.valueOf(importPrice));
				productImport.setImportUnit(importUnit);
				productImport.setDateTime(importDate);
				importRepository.save(productImport);
				System.out.println("Searching for Product with ModelId=" + modelId + " ColorId=" + colorId);

			}
		}catch (ApiException e) {
		    throw e; 
		} 
		catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To read Exel File");
		}
		
	}
	@Override
	public Product findByModelIdAndColorId(Long modelId, Long colorId) {
		String text = String.format("Product with Model id = %d and Color id = %d not found", modelId, colorId);
		return productRepository.findByModelIdAndColorId(modelId, colorId)
					.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, text));
	}

}
