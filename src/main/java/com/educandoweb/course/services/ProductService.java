package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.domain.ProductDomain;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public List<ProductDomain> findAll(){
		return repository.findAll();
		
	}

	public ProductDomain findById(Long id) {
		Optional<ProductDomain> obj = repository.findById(id);
		return obj.get();
	}

}
