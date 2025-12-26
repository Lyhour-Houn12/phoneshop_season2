package com.lyhour.java.developer.phoneshop.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.lyhour.java.developer.phoneshop.entity.ProductImport;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailSpec implements Specification<ProductImport>{
	private final ProductDetailFilter detailFilter;
	
	@Override
	public Predicate toPredicate(Root<ProductImport> productImport, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(detailFilter.getStartDate())) {
			predicates.add(cb.greaterThanOrEqualTo(productImport.get("dateTime"), detailFilter.getStartDate()));
		}
		if(Objects.nonNull(detailFilter.getEndDate())) {
			predicates.add(cb.lessThanOrEqualTo(productImport.get("dateTime"), detailFilter.getEndDate()));
		}
		Predicate[] ppA = predicates.toArray(Predicate[]::new);
		return cb.and(ppA);
	}
	
}
