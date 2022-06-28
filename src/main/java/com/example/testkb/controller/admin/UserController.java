package com.example.testkb.controller.admin;

import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/kb/admin")
public class UserController {

    private final UserEndpoint userEndpoint;

    public UserController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @PostMapping("/create-cashier")
    public ResponseEntity<UserView> createCashier(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userEndpoint.create(request));
    }
}
