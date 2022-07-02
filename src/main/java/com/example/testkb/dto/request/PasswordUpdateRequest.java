package com.example.testkb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PasswordUpdateRequest {
    private String oldPassword;
    private String newPassword;
}
