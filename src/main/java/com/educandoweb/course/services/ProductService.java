package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.dto.request.CreateProductRequest;
import com.educandoweb.course.model.dto.request.UpdateProductRequest;
import com.educandoweb.course.model.dto.response.GetProductResponse;
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
		ProductDomain domain = ProductDomain.valueOf(request);
		repository.save(domain);
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

	public void updateProduct(Long id, UpdateProductRequest updateProduct) {
		Optional<ProductDomain> domain = repository.findById(id);
		if (domain.isPresent()) {
			updateData(domain.get(), updateProduct);
		} else {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(ProductDomain productDomain, UpdateProductRequest updateProductRequest) {
		productDomain.setName(updateProductRequest.getName());
		productDomain.setDescription(updateProductRequest.getDescription());
		productDomain.setPrice(updateProductRequest.getPrice());
		productDomain.setCategory(updateProductRequest.getCategory());
		repository.save(productDomain);
	}

}
