package com.example.testkb.endpoint;

import com.example.testkb.dto.request.SignInRequest;
import com.example.testkb.dto.response.JWTAuthenticationResponse;

public interface AuthEndpoint {
    JWTAuthenticationResponse signIn(SignInRequest request);
}
