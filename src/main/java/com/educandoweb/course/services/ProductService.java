package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.dto.request.CreateProductRequest;
import com.educandoweb.course.model.dto.request.UpdateProductRequest;
import com.educandoweb.course.model.dto.response.GetProductResponse;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repository;
	private final CategoryRepository categoryRepository;

	public List<GetProductResponse> getAllProducts(){
		List<ProductDomain> productDomain = repository.findAll();
		return productDomain.stream()
				.map(GetProductResponse::valueOf)
				.collect(Collectors.toList());
	}

	public GetProductResponse getProductById(Long id) {
		Optional<ProductDomain> productDomain = repository.findById(id);
		GetProductResponse productResponse = GetProductResponse.valueOf(productDomain.get());
		return productResponse;
	}

	public void createProduct(CreateProductRequest request){
		Optional<CategoryDomain> categoryDomain = categoryRepository.findById(request.getId());
		if (categoryDomain.isPresent()){
			ProductDomain domain = ProductDomain.valueOf(request, categoryDomain.get());
			repository.save(domain);
		} else {
			throw new ResourceNotFoundException(request.getId());
		}

	}

	public void deleteProduct (Long id){
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public void updateProduct(Long id, UpdateProductRequest updateProductRequest) {
		Optional<ProductDomain> domain = repository.findById(id);
		if (domain.isPresent()) {
			Optional<CategoryDomain> categoryDomain = categoryRepository.findById(updateProductRequest.getIdCategory());
			if (categoryDomain.isPresent()) {
				updateData(domain.get(), updateProductRequest, categoryDomain.get());
			} else {
				throw new ResourceNotFoundException(updateProductRequest.getIdCategory());
			}
		} else {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(ProductDomain productDomain, UpdateProductRequest updateProductRequest, CategoryDomain categoryDomain ) {
		productDomain.setName(updateProductRequest.getName());
		productDomain.setDescription(updateProductRequest.getDescription());
		productDomain.setPrice(updateProductRequest.getPrice());
		productDomain.setCategory(categoryDomain);
		repository.save(productDomain);
	}

}
