package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.educandoweb.course.model.dto.request.UpdateUserRequest;
import com.educandoweb.course.model.dto.response.GetUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.model.domain.UserDomain;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<GetUserResponse> getAllUsers() {
        log.info("Getting all Users");
        List<UserDomain> domain = repository.findAll();
        return domain.stream()
                .map(GetUserResponse::valueOf)
                .collect(Collectors.toList());
    }

    public GetUserResponse getUserById(Long id) {
        log.info("Getting user by id {}", id);
        Optional<UserDomain> optionalUserDomain = repository.findById(id);

        log.info("Category Id request {} Not Found", id);
        UserDomain userDomain = optionalUserDomain.orElseThrow(() -> new ResourceNotFoundException(id));
        GetUserResponse response = GetUserResponse.valueOf(userDomain);
        return response;
    }

    public void createUser(CreateUserRequest request) {
        log.info("Creating new User");
        UserDomain domain = UserDomain.valueOf(request);
        repository.save(domain);
    }

    public void deleteUser(long id) {
        try {
            log.info("Deleting user by Id {}", id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.info("Id request {} not found", id);
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateUser(Long id, UpdateUserRequest updateUser) {
        log.info("Updating an existing user");
        Optional<UserDomain> optionalUser = repository.findById(id);

        log.info("User Id request {} Not Found", id);
        UserDomain userDomain = optionalUser.orElseThrow(() -> new ResourceNotFoundException(id));
            updateUserFields(userDomain, updateUser);
        }

    private void updateUserFields(UserDomain userDomain, UpdateUserRequest updateUserRequest) {
        log.info("Setting User's fields that requested");
        userDomain.setName(updateUserRequest.getName());
        userDomain.setEmail(updateUserRequest.getEmail());
        userDomain.setPhone(updateUserRequest.getPhone());
        userDomain.setPassword(updateUserRequest.getPassword());

        log.info("Saving user updating");
		repository.save(userDomain);
    }

}
