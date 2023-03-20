package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.dto.response.GetOrderResponse;
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

	public List<GetOrderResponse> findAll() {
		List<OrderDomain> orderDomain = repository.findAll();
		return orderDomain.stream()
				.map(GetOrderResponse::valueOf)
				.collect(Collectors.toList());
	}

	public GetOrderResponse findById(Long id) {
		Optional<OrderDomain> orderDomain = repository.findById(id);
		GetOrderResponse orderResponse = GetOrderResponse.valueOf(orderDomain.get());
		return orderResponse;
	}

}
