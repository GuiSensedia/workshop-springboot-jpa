package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository repository;

	public List<CategoryDomain> findAll(){
		return repository.findAll();
	}

	public CategoryDomain findById(Long id) {
		Optional<CategoryDomain> obj = repository.findById(id);
		return obj.get();
	}

}
