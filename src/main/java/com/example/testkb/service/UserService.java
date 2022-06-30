package com.example.testkb.service;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;

public interface UserService {
    User create(UserCreateRequest request, Bank bank, String encodedPassword, Role role);
    User changePassword(PasswordUpdateRequest request, @CurrentUser UserPrincipal currentUser);
    User getById(Long id);
    User getByUsername(String username);
}
