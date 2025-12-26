package com.lyhour.java.developer.phoneshop.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.lyhour.java.developer.phoneshop.entity.Model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class ModelSpec implements Specification<Model>{
	private final ModelFilter modelFilter;

	List<Predicate> predicates = new ArrayList<>();
	@Override
	public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(modelFilter.getName() != null) {
			Predicate name = cb.like(cb.upper(model.get("name")), "%" +  modelFilter.getName().toUpperCase() + "%");
			predicates.add(name);
		}
		return cb.and(predicates.toArray(Predicate[]::new));
	}

	
	
}
