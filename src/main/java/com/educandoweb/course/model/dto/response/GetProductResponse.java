package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.controller.ProductController;
import com.educandoweb.course.model.domain.ProductDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {

    private String name;

    private String description;

    private Double price;

    public static GetProductResponse valueOf(ProductDomain productDomain){
        GetProductResponse productResponse = GetProductResponse.builder()
                .name(productDomain.getName())
                .description(productDomain.getDescription())
                .price(productDomain.getPrice())
                .build();
        return productResponse;
    }

}
