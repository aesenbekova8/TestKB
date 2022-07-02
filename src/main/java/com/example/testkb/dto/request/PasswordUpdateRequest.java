package com.example.testkb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordUpdateRequest {
    private String oldPassword;
    private String newPassword;
}
