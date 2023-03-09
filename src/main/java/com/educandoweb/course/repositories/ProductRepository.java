package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.domain.ProductDomain;

public interface ProductRepository extends JpaRepository<ProductDomain, Long> {
	

}
