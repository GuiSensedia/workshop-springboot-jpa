package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.OrderDomain;
import com.educandoweb.course.model.domain.PaymentDomain;
import com.educandoweb.course.model.domain.UserDomain;
import com.educandoweb.course.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentDomain paymentDomain;

    @Mock
    private UserDomain userDomain;

    @InjectMocks
    private OrderService orderService;


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
        List<OrderDomain> orderDomainList = new ArrayList<>();
        orderDomainList.add(new OrderDomain(1, Instant.parse("2019-07-20T10:20:30Z"), 3, userDomain, paymentDomain));
        orderDomainList.add(new OrderDomain(2, Instant.parse("2019-07-21T11:21:31Z"), 3, userDomain, paymentDomain));
    }

    private void givenOrderFindByIdReturnsOrderDomain() {
        Optional<OrderDomain> optionalOrderDomain = Optional.of(OrderDomain.builder()
                        .id(3)
                        .moment(Instant.parse("2019-07-22T12:22:32Z"))
                        .orderStatus(4)
                        .client(userDomain)
                        .payment(paymentDomain)
                        .build());
        when(orderRepository.findById(anyLong())).thenReturn(optionalOrderDomain);
    }

    /*
    WHEN METHODS
    */
    private void whenGetAllOrdersCalled() {
        orderService.getAllOrders();
    }
    private void whenGetOrderByIdCalled() {
        orderService.getOrderById(1L);
    }

    /*
    THEN METHODS
    */
    private void thenExpectedOrderRepositoryFindAllCalledOnce() {
        verify(orderRepository,times(1)).findAll();
    }
    private void thenExpectedOrderRepositoryFindByIdCalledOnce() {
        verify(orderRepository,times(1)).findById(anyLong());
    }

}