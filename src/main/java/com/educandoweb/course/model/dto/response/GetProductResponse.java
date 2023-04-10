package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.controller.ProductController;
import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.domain.ProductDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String category;

    public static GetProductResponse valueOf(ProductDomain productDomain){
        GetProductResponse productResponse = GetProductResponse.builder()
                .id(productDomain.getId())
                .name(productDomain.getName())
                .description(productDomain.getDescription())
                .category(productDomain.getCategory().getName())
                .price(productDomain.getPrice())
                .build();
        return productResponse;
    }

}
