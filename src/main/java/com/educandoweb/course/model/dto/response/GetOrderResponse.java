package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.enums.OrderStatus;
import com.educandoweb.course.model.domain.OrderDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private OrderStatus orderStatus;

    private List<GetOrderItemResponse> orderItem = new ArrayList<>();

    public static GetOrderResponse valueOf(OrderDomain orderDomain){
        GetOrderResponse orderResponse = GetOrderResponse.builder()
                .moment(orderDomain.getMoment())
                .orderStatus(OrderStatus.valueOf(orderDomain.getOrderStatus()))
                .orderItem(orderDomain.getItems()
                        .stream()
                        .map(GetOrderItemResponse::valueOf).collect(Collectors.toList()))
                .build();
        return orderResponse;
    }
}
