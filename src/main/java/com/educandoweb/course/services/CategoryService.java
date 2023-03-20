package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.model.dto.response.GetCategoryResponse;
import com.educandoweb.course.model.dto.response.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository repository;

	public List<GetCategoryResponse> findAll(){
		List<CategoryDomain> categoryDomain = repository.findAll();
		return categoryDomain.stream()
				.map(GetCategoryResponse::valueOf)
				.collect(Collectors.toList());
	}

	public GetCategoryResponse findById(Long id) {
		Optional<CategoryDomain> categoryDomain = repository.findById(id);
		GetCategoryResponse categoryResponse = GetCategoryResponse.valueOf(categoryDomain.get());
		return categoryResponse;
	}

}
