package com.lukyanov.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String email;
}
