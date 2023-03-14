package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.domain.CategoryDomain;

public interface CategoryRepository extends JpaRepository<CategoryDomain, Long> {

}
