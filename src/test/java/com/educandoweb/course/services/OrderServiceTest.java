package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.OrderDomain;
import com.educandoweb.course.model.domain.UserDomain;
import com.educandoweb.course.model.dto.request.CreateUserRequest;
import com.educandoweb.course.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldGetAllOrders() {
        givenOrdersFindAllReturnsListOrderDomain();
        whenGetAllOrdersCalled();
        thenExpectedOrderRepositoryFindAllCalledOnce();
    }


    @Test
    void shouldGetOrderById() {
        givenOrderFindByIdReturnsOrderDomain();
        whenGetOrderByIdCalled();
        thenExpectedOrderRepositoryFindByIdCalledOnce();
    }


    /*
    GIVEN METHODS
    */
    private void givenOrdersFindAllReturnsListOrderDomain() {
        UserDomain userDomain = UserDomain.builder()
                .name("Gabriel")
                .email("gabriel@gmail.com")
                .phone("1888888888")
                .password("987456321")
                .build();
        List<OrderDomain> orderDomainList = new ArrayList<>();
//        orderDomainList.add(new OrderDomain(1,14/04/2023,3,userDomain,))
    }

    private void givenOrderFindByIdReturnsOrderDomain() {
    }

    /*
    WHEN METHODS
    */
    private void whenGetAllOrdersCalled() {
    }
    private void whenGetOrderByIdCalled() {
    }

    /*
    THEN METHODS
    */
    private void thenExpectedOrderRepositoryFindAllCalledOnce() {
    }
    private void thenExpectedOrderRepositoryFindByIdCalledOnce() {
    }
}