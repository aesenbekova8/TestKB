package com.example.testkb.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}