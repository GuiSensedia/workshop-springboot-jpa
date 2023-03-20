package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.response.GetCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<GetCategoryResponse> findAll() {
		List<GetCategoryResponse> listCategories = service.findAll();
		return listCategories;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GetCategoryResponse findById(@PathVariable Long id){
		GetCategoryResponse categoryResponse = service.findById(id);
		return categoryResponse;
	}
	
}