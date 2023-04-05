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
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.repositories.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repository;

	private final CategoryRepository categoryRepository;

	public List<GetProductResponse> getAllProducts(){
		log.info("Getting all products");
		List<ProductDomain> productDomain = repository.findAll();
		return productDomain.stream()
				.map(GetProductResponse::valueOf)
				.collect(Collectors.toList());
	}

	public GetProductResponse getProductById(Long id) {
		log.info("Getting Product by Id");
		Optional<ProductDomain> productDomain = repository.findById(id);
		GetProductResponse productResponse = GetProductResponse.valueOf(productDomain.get());
		return productResponse;
	}

	public void createProduct(CreateProductRequest request){
		log.info("Finding Category informed in request");
		Optional<CategoryDomain> categoryDomain = categoryRepository.findById(request.getId());
		if (categoryDomain.isPresent()){
			log.info("Creating and saving new product");
			ProductDomain domain = ProductDomain.valueOf(request, categoryDomain.get());
			repository.save(domain);
		} else {
			log.info("CategoryId {} requested Not Found", request.getId());
			throw new ResourceNotFoundException(request.getId());
		}
	}

	public void deleteProduct (Long id){
		try {
			log.info("Deleting Product with id {}", id);
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			log.info("Id {}",id + " Not Found");
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public void updateProduct(Long id, UpdateProductRequest updateProductRequest) {
		log.info("Finding product with id {}", id);
		Optional<ProductDomain> optionalProduct = repository.findById(id);
		ProductDomain productDomain = optionalProduct.orElseThrow(() -> new ResourceNotFoundException(id));
		log.info("Finding Category with id {}", updateProductRequest.getIdCategory());
		Optional<CategoryDomain> optionalCategory = categoryRepository.findById(updateProductRequest.getIdCategory());
		CategoryDomain categoryDomain = optionalCategory.orElseThrow(() -> new ResourceNotFoundException(updateProductRequest.getIdCategory()));
		log.info("Saving new product in database");
		updateProductsFields(productDomain, updateProductRequest, categoryDomain);
	}

	private void updateProductsFields(ProductDomain productDomain, UpdateProductRequest updateProductRequest, CategoryDomain categoryDomain ) {
		log.info("Setting Product's data that update");
		productDomain.setName(updateProductRequest.getName());
		productDomain.setDescription(updateProductRequest.getDescription());
		productDomain.setPrice(updateProductRequest.getPrice());
		productDomain.setCategory(categoryDomain);
		repository.save(productDomain);
	}

}
