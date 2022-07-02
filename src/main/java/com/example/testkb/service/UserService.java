package com.example.testkb.service;

import com.example.testkb.entity.User;

public interface UserService {
    User create(User user);
    User changePassword(User user, String newPassword);
    User getById(Long id);
    User getByUsername(String username);
}
