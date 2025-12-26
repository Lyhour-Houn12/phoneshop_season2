package com.lyhour.java.developer.phoneshop.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.lyhour.java.developer.phoneshop.entity.Sale;
import com.lyhour.java.developer.phoneshop.entity.SaleDetails;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class SaleDetailsSpec implements Specification<SaleDetails>{
	private SaleDetailsFilter detailsFilter;
	
	
	@Override
	public Predicate toPredicate(Root<SaleDetails> saleDetails, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		Join<SaleDetails, Sale> sale = saleDetails.join("sale");
		if(Objects.nonNull(detailsFilter.getStartDate())) {
			Predicate startDate = cb.greaterThanOrEqualTo(sale.get("dateTime"), detailsFilter.getStartDate());
			predicates.add(startDate);
		}
		if(Objects.nonNull(detailsFilter.getEndDate())) {
			Predicate endDate = cb.lessThanOrEqualTo(sale.get("dateTime"), detailsFilter.getEndDate());
			predicates.add(endDate);
		}
		Predicate[] ppA = predicates.toArray(Predicate[]::new);
		return cb.and(ppA);
	}
	
}
