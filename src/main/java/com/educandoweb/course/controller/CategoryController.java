package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.request.CreateCategoryRequest;
import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.educandoweb.course.model.dto.request.UpdateCategoryRequest;
import com.educandoweb.course.model.dto.request.UpdateUserRequest;
import com.educandoweb.course.model.dto.response.GetCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GetCategoryResponse> getAllCategories() {
		List<GetCategoryResponse> listCategories = service.getAllCategories();
		return listCategories;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GetCategoryResponse getCategoryById(@PathVariable Long id){
		GetCategoryResponse categoryResponse = service.getCategoryById(id);
		return categoryResponse;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCategory (@RequestBody @Valid CreateCategoryRequest request){
		service.createCategory(request);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable Long id){
		service.deleteCategory(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateCategory (@PathVariable Long id, @RequestBody @Valid UpdateCategoryRequest updateCategory){
		service.updateCategory(id, updateCategory);
	}
	
}