package com.example.testkb.endpoint.impl;

import com.example.testkb.config.security.JWTTokenProvider;
import com.example.testkb.dto.request.SignInRequest;
import com.example.testkb.dto.response.JWTAuthenticationResponse;
import com.example.testkb.endpoint.AuthEndpoint;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.service.AuthService;
import com.example.testkb.service.UserService;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AuthEndpointImpl implements AuthEndpoint {

    private final UserService userService;
    private final AuthService authService;
    private final JWTTokenProvider jwtTokenProvider;

    public AuthEndpointImpl(UserService userService,
                            AuthService authService,
                            JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public JWTAuthenticationResponse signIn(@NonNull SignInRequest request) {
        User user = userService.getByUsername(request.getUsername());
        Authentication authentication = authService.getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        Set<Role> roles = user.getRoles();

        return new JWTAuthenticationResponse(jwt, roles);
    }
}
