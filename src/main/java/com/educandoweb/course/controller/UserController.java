package com.educandoweb.course.controller;

import java.util.List;

import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.educandoweb.course.model.dto.request.UpdateUserRequest;
import com.educandoweb.course.model.dto.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GetUserResponse> getAllUsers() {
		List<GetUserResponse> responseList = service.getAllUsers();
		return responseList;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GetUserResponse getUserById(@PathVariable Long id){
		GetUserResponse response = service.getUserById(id);
		return response;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser (@RequestBody CreateUserRequest request){
		service.createUser(request);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id){
		service.deleteUser(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateUser (@PathVariable Long id, @RequestBody UpdateUserRequest updateUser){
		service.updateUser(id, updateUser);
	}

}