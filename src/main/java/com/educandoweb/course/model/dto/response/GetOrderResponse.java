package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.model.domain.OrderDomain;
import com.educandoweb.course.model.domain.OrderItemDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Literal;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    private List<OrderItemDomain> orderItem = new ArrayList<>();

    public static GetOrderResponse valueOf(OrderDomain orderDomain){
        GetOrderResponse oderResponse = GetOrderResponse.builder()
                .moment(orderDomain.getMoment())
                .orderStatus(orderDomain.getOrderStatus())
                .orderItem(orderDomain.getItems())
                .build();
        return oderResponse;
    }
}
