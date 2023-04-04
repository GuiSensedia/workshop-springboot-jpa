package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.request.CreateProductRequest;
import com.educandoweb.course.model.dto.request.UpdateProductRequest;
import com.educandoweb.course.model.dto.request.UpdateUserRequest;
import com.educandoweb.course.model.dto.response.GetProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct (@RequestBody @Valid CreateProductRequest request){
		service.createProduct(request);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct (@PathVariable Long id){
		service.deleteProduct(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct (@PathVariable Long id, @RequestBody @Valid UpdateProductRequest updateProduct){
		service.updateProduct(id, updateProduct);
	}

}