package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.model.domain.UserDomain;

public interface UserRepository extends JpaRepository<UserDomain, Long> {
	

}
