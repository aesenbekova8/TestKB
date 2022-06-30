package com.example.testkb.controller.general;

import com.example.testkb.dto.request.SignInRequest;
import com.example.testkb.dto.response.JWTAuthenticationResponse;
import com.example.testkb.endpoint.AuthEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/kb/auth")
public class AuthController {

    private final AuthEndpoint authEndpoint;

    public AuthController(AuthEndpoint authEndpoint) {
        this.authEndpoint = authEndpoint;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        JWTAuthenticationResponse response = authEndpoint.signIn(signInRequest);
        return ResponseEntity.ok(response);
    }


}
