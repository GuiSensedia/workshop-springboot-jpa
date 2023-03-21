package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.response.GetProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GetProductResponse> getAllProducts() {
		List<GetProductResponse> listProduct = service.getAllProducts();
		return listProduct;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GetProductResponse getProductById(@PathVariable Long id){
		GetProductResponse productResponse = service.getProductById(id);
		return productResponse;
	}
	
}