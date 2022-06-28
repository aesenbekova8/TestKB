package com.example.testkb.service;

import com.example.testkb.dto.request.SignInRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication getAuthentication(SignInRequest request);
}
