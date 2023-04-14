package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.domain.UserDomain;
import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.educandoweb.course.model.dto.request.UpdateUserRequest;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private CreateUserRequest createUserRequest;

    @InjectMocks
    private UpdateUserRequest updateUserRequest;

    @Test
    void shouldGetAllUsers() {
        givenUserFindAllReturnsListUserDomain();
        whenGetAllUsersCalled();
        thenExpectedUserRepositoryFindAllCalledOnce();
    }

    @Test
    void shouldGetUserById() {
        givenUserFindByIdReturnsUserDomain();
        whenGetUserByIdCalled();
        thenExpectUserRepositoryFindByIdCalledOnce();
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenFindUserByIdNotFound() {
        givenUserFindByIdReturnsEmpty();
        whenGetUserByIdCalledThrowsResourceNotFoundException();
        thenExpectUserRepositoryFindByIdCalledOnce();
    }

    @Test
    void shouldCreateUser() {
        givenCreateUserRequest();
        whenCreateUserCalled();
        thenExpectUserRepositorySaveCalledOnce();
    }

    @Test
    void shouldDeleteUser() {
        whenDeleteUserCalled();
        thenExpectUserRepositoryDeleteByIdCalledOnce();
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenDeleteUserNotFound() {
        givenUserRepositoryDeleteByIdThrowsException();
        whenUserDeleteByIdCalledThrowsResourceNotFoundException();
        thenExpectUserRepositoryDeleteByIdCalledOnce();
    }

    @Test
    void shouldUpdateUser() {
        givenUserFindByIdReturnsUserDomain();
        givenUpdateUserRequest();
        whenUpdateUserCalled();
        thenExpectUserRepositoryFindByIdCalledOnce();
        thenExpectUserRepositorySaveCalledOnce();
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUpdateUserIdNotFound() {
        givenUserFindByIdReturnsEmpty();
        whenGetUserByIdForUpdateCalledThrowsResourceNotFoundException();
        thenExpectUserRepositoryFindByIdCalledOnce();
    }

    /*
     * GIVEN METHODS
     */

    private void givenUserFindAllReturnsListUserDomain() {
        List<UserDomain> listUser = new ArrayList<>();
        UserDomain userDomain1 = UserDomain.builder()
                .name("Guilherme")
                .email("guilherme@gmail.com")
                .phone("19999999999")
                .password("123654789")
                .build();
        UserDomain userDomain2 = UserDomain.builder()
                .name("Gabriel")
                .email("gabriel@gmail.com")
                .phone("1888888888")
                .password("987456321")
                .build();
        listUser.add(userDomain1);
        listUser.add(userDomain2);
        when(userRepository.findAll()).thenReturn(listUser);
    }

    private void givenUserFindByIdReturnsUserDomain() {
        Optional<UserDomain> optionalUserDomain = Optional.of(UserDomain.builder()
                .name("Guilherme")
                .email("guilherme@gmail.com")
                .phone("19999999999")
                .password("123654789")
                .build());
        when(userRepository.findById(anyLong())).thenReturn(optionalUserDomain);
    }

    private void givenUserFindByIdReturnsEmpty() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
    }


    private void givenUserRepositoryDeleteByIdThrowsException() {
        doThrow(EmptyResultDataAccessException.class).when(userRepository).deleteById(anyLong());
    }

    private void givenCreateUserRequest() {
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .name("Gabriel")
                .email("gabriel@gmail.com")
                .phone("1888888888")
                .password("987456321")
                .build();
        createUserRequest = userRequest;
    }

    private void givenUpdateUserRequest() {
        UpdateUserRequest userRequest = UpdateUserRequest.builder()
                .name("Pedro")
                .email("pedro@gmail.com")
                .phone("1777777777")
                .password("147896325")
                .build();
    }

    /*
     * WHEN METHODS
     */

    private void whenGetAllUsersCalled() {
        userService.getAllUsers();
    }

    private void whenGetUserByIdCalled() {
        userService.getUserById(1L);
    }


    private void whenGetUserByIdCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(2L));
    }

    private void whenCreateUserCalled() {
        userService.createUser(createUserRequest);
    }

    private void whenDeleteUserCalled() {
        userService.deleteUser(3L);
    }

    private void whenUserDeleteByIdCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(4L));
    }

    private void whenUpdateUserCalled() {
        userService.updateUser(5L, updateUserRequest);
    }

    private void whenGetUserByIdForUpdateCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(6L));
    }

    /*
     * THEN METHODS
     */

    private void thenExpectedUserRepositoryFindAllCalledOnce() {
        verify(userRepository,times(1)).findAll();
    }

    private void thenExpectUserRepositoryFindByIdCalledOnce() {
        verify(userRepository, times(1)).findById(anyLong());
    }

    private void thenExpectUserRepositorySaveCalledOnce() {
        verify(userRepository, times(1)).save(any(UserDomain.class));
    }
    private void thenExpectUserRepositoryDeleteByIdCalledOnce() {
        verify(userRepository, times(1)).deleteById(anyLong());
    }

}
