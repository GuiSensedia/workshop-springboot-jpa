package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.dto.response.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repository;

	public List<GetProductResponse> findAll(){
		List<ProductDomain> productDomain = repository.findAll();
		return productDomain.stream()
				.map(GetProductResponse::valueOf)
				.collect(Collectors.toList());
	}

	public GetProductResponse findById(Long id) {
		Optional<ProductDomain> productDomain = repository.findById(id);
		GetProductResponse productResponse = GetProductResponse.valueOf(productDomain.get());
		return productResponse;
	}

}
