package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.model.dto.request.CreateCategoryRequest;
import com.educandoweb.course.model.dto.request.UpdateCategoryRequest;
import com.educandoweb.course.model.dto.response.GetCategoryResponse;
import com.educandoweb.course.model.dto.response.GetProductResponse;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.repositories.CategoryRepository;
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository repository;

	public List<GetCategoryResponse> getAllCategories(){
		log.info("Getting all categories");
		List<CategoryDomain> categoryDomain = repository.findAll();
		return categoryDomain.stream()
				.map(GetCategoryResponse::valueOf)
				.collect(Collectors.toList());
	}

	public GetCategoryResponse getCategoryById(Long id) {
		log.info("Getting an category by Id {}", id);
		Optional<CategoryDomain> categoryDomain = repository.findById(id);
		GetCategoryResponse categoryResponse = GetCategoryResponse.valueOf(categoryDomain.get());
		return categoryResponse;
	}

      public void createCategory(CreateCategoryRequest request) {
		log.info("Creating new Category");
		CategoryDomain domain = CategoryDomain.valueOf(request);
		repository.save(domain);
	}

	public void deleteCategory(long id) {
		try {
			log.info("Deleting category by Id {}", id);
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			log.info("Id request {} not found", id);
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public void updateCategory(Long id, UpdateCategoryRequest updateCategory) {
		log.info("Updating an existing category");
		Optional<CategoryDomain> optionalCategory = repository.findById(id);

		log.info("Category Id request {} Not Found", id);
		CategoryDomain categoryDomain = optionalCategory.orElseThrow(() -> new ResourceNotFoundException(id));
		updateCategoryFields(categoryDomain, updateCategory);
	}

	private void updateCategoryFields(CategoryDomain categoryDomain, UpdateCategoryRequest updateCategoryRequest) {
		log.info("Setting Category's fields that requested");
		categoryDomain.setName(updateCategoryRequest.getName());

		log.info("Saving category updating");
		repository.save(categoryDomain);
	}

}
