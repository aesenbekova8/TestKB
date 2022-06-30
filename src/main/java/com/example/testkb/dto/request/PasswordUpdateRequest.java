package com.example.testkb.dto.request;

import lombok.Data;

@Data
public class PasswordUpdateRequest {
    private String oldPassword;
    private String newPassword;
}
