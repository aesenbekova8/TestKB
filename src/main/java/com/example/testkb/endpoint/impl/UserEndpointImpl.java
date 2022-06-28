package com.example.testkb.endpoint.impl;

import com.example.testkb.config.security.JWTTokenProvider;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.request.SignInRequest;
import com.example.testkb.dto.response.JWTAuthenticationResponse;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import com.example.testkb.entity.Role;
import com.example.testkb.entity.User;
import com.example.testkb.mapper.UserViewMapper;
import com.example.testkb.service.AuthService;
import com.example.testkb.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserEndpointImpl implements UserEndpoint {

    private final UserService userService;
    private final AuthService authService;
    private final JWTTokenProvider jwtTokenProvider;
    private final UserViewMapper userViewMapper;

    @Autowired
    public UserEndpointImpl(UserService userService,
                            AuthService authService,
                            JWTTokenProvider jwtTokenProvider,
                            UserViewMapper userViewMapper) {
        this.userService = userService;
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userViewMapper = userViewMapper;
    }

    @Override
    @Transactional
    public UserView create(@NonNull UserCreateRequest request) {
        return userViewMapper.toUserView(userService.create(request));
    }

    @Override
    @Transactional
    public UserView updatePassword(@NonNull PasswordUpdateRequest request) {
        return userViewMapper.toUserView(userService.changePassword(request));
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
