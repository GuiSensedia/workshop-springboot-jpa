package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.model.domain.OrderItemDomain;

public interface OrderItemRepository extends JpaRepository<OrderItemDomain, Long> {
	

}
