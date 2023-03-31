package com.educandoweb.course.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    @Size(min = 8, max = 12)
    private String password;

}
