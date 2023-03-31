package com.educandoweb.course.model.dto.request;

import com.educandoweb.course.model.domain.CategoryDomain;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductRequest {
    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private Double price;
    @NotEmpty
    private CategoryDomain category;

}
