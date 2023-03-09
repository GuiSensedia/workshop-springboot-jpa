package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.domain.OrderDomain;
import com.educandoweb.course.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	public List<OrderDomain> findAll(){
		return repository.findAll();
		
	}
	public OrderDomain findById(Long id) {
		Optional<OrderDomain> obj = repository.findById(id);
		return obj.get();
	}
}
