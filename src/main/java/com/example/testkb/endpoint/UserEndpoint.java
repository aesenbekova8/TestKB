package com.example.testkb.endpoint;

import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.request.SignInRequest;
import com.example.testkb.dto.response.JWTAuthenticationResponse;
import com.example.testkb.dto.response.UserView;

public interface UserEndpoint {
    UserView create(UserCreateRequest request);
    UserView updatePassword(PasswordUpdateRequest request);
    JWTAuthenticationResponse signIn(SignInRequest request);
}
