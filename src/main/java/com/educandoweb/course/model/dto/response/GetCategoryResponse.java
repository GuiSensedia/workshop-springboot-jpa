package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.domain.ProductDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryResponse {

    private String name;

    private Set<ProductDomain> products = new HashSet<>();

    public static GetCategoryResponse valueOf(CategoryDomain categoryDomain){
        GetCategoryResponse categoryResponse = GetCategoryResponse.builder()
                .name(categoryDomain.getName())
                .products(categoryDomain.getProducts())
                .build();
        return categoryResponse;
    }

}
