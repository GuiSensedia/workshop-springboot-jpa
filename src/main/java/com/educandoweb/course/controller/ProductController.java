package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.response.GetProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GetProductResponse> findAll() {
		List<GetProductResponse> listProduct = service.findAll();
		return listProduct;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GetProductResponse findById(@PathVariable Long id){
		GetProductResponse productResponse = service.findById(id);
		return productResponse;
	}
	
}