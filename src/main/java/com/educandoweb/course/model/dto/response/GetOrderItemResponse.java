package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.model.domain.OrderItemDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderItemResponse {

    private String nameProduct;
    private Integer quantity;
    private Double price;
    private Double subTotal;

    public static GetOrderItemResponse valueOf(OrderItemDomain orderItem){
        GetOrderItemResponse getOrderItemResponse = GetOrderItemResponse.builder()
                .nameProduct(orderItem.getId().getProduct().getName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .subTotal(orderItem.getSubTotal())
                .build();
        return getOrderItemResponse;
    }

}
