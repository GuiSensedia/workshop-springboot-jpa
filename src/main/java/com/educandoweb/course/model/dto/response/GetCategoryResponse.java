package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.model.domain.CategoryDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryResponse {

    private String name;

    public static GetCategoryResponse valueOf(CategoryDomain categoryDomain){
        GetCategoryResponse categoryResponse = GetCategoryResponse.builder()
                .name(categoryDomain.getName())
                .build();
        return categoryResponse;
    }

}
