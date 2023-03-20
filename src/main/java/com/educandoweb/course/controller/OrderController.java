package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.response.GetOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.model.domain.OrderDomain;
import com.educandoweb.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired

	private OrderService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GetOrderResponse> findAll() {
		List<GetOrderResponse> listOrder = service.findAll();
		return listOrder;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GetOrderResponse findById(@PathVariable Long id){
		GetOrderResponse orderResponse = service.findById(id);
		return orderResponse;
	}
	
}