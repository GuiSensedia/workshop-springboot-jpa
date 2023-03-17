package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.educandoweb.course.model.dto.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.model.domain.UserDomain;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<GetUserResponse>> findAll() {
		List<GetUserResponse> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GetUserResponse findUserById(@PathVariable Long id){
		GetUserResponse response = service.findUserById(id);
		return response;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser (@RequestBody CreateUserRequest request){
		service.createUser(request);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDomain> update (@PathVariable Long id, @RequestBody UserDomain obj){
		obj = service.updateUser(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}