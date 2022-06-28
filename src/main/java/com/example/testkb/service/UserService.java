package com.example.testkb.service;

import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.entity.User;

public interface UserService {
    User create(UserCreateRequest request);
    User changePassword(PasswordUpdateRequest request);
    User getById(Long id);
    User getByUsername(String username);
}
