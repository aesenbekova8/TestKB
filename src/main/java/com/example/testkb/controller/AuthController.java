package com.example.testkb.controller;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.request.SignInRequest;
import com.example.testkb.dto.response.JWTAuthenticationResponse;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/kb/api/auth")
public class AuthController {

    private final UserEndpoint userEndpoint;

    @Autowired
    public AuthController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        JWTAuthenticationResponse response = userEndpoint.signIn(signInRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-password")
    public ResponseEntity<UserView> changePassword(@Valid @RequestBody PasswordUpdateRequest request) {
        return ResponseEntity.ok(userEndpoint.updatePassword(request));
    }
}
