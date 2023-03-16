package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.OrderDomain;
import com.educandoweb.course.repositories.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;

	public List<OrderDomain> findAll(){
		return repository.findAll();
	}

	public OrderDomain findById(Long id) {
		Optional<OrderDomain> obj = repository.findById(id);
		return obj.get();
	}

}
