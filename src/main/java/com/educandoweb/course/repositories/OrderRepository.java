package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.domain.OrderDomain;

public interface OrderRepository extends JpaRepository<OrderDomain, Long> {
	

}
