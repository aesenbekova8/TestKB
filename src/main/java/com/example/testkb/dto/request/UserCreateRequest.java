package com.example.testkb.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserCreateRequest {

    @NotBlank @Email
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Long bankId;

}