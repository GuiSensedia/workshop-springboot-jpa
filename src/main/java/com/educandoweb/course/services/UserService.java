package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.educandoweb.course.model.dto.request.UpdateUserRequest;
import com.educandoweb.course.model.dto.response.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.UserDomain;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

//	public List<UserDomain> findAll(){
//		return repository.findAll();
//	}

    public List<GetUserResponse> findAll() {
        List<UserDomain> domain = repository.findAll();
        return domain.stream()
                .map(GetUserResponse::valueOf)
                .collect(Collectors.toList());
    }

    public GetUserResponse findUserById(Long id) {
        Optional<UserDomain> domain = repository.findById(id);
        GetUserResponse response = GetUserResponse.valueOf(domain.get());
        return response;
    }

    public void createUser(CreateUserRequest request) {
        UserDomain domain = UserDomain.valueOf(request);
        repository.save(domain);
    }

    public void deleteUser(long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateUser(Long id, UpdateUserRequest updateUser) {
        Optional<UserDomain> domain = repository.findById(id);
        if (domain.isPresent()) {
            updateData(domain.get(), updateUser);
		} else {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(UserDomain userDomain, UpdateUserRequest updateUserRequest) {
        userDomain.setName(updateUserRequest.getName());
        userDomain.setEmail(updateUserRequest.getEmail());
        userDomain.setPhone(updateUserRequest.getPhone());
		repository.save(userDomain);
    }
}
