package com.educandoweb.course.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Min(0)
    private Double price;
    @NotNull
    private Long id;

}
