package com.educandoweb.course.model.dto.response;

import com.educandoweb.course.model.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    private String name;

    private String email;

    private String phone;

    private String password;

    public static GetUserResponse valueOf(UserDomain domain){
        GetUserResponse response = GetUserResponse.builder()
                .name(domain.getName())
                .email(domain.getEmail())
                .phone(domain.getPhone())
                .password(domain.getPassword())
                .build();

        return response;
    }

}
