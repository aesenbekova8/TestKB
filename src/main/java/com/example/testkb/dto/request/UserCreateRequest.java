package com.example.testkb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}