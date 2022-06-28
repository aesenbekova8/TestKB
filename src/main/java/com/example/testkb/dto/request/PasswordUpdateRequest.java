package com.example.testkb.dto.request;

import lombok.Data;

@Data
public class PasswordUpdateRequest {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
