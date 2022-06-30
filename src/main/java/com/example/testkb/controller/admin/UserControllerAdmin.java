package com.example.testkb.controller.admin;

import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/kb/admin")
public class UserControllerAdmin {

    private final UserEndpoint userEndpoint;

    public UserControllerAdmin(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @PostMapping("/create-cashier")
    public ResponseEntity<UserView> createCashier(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userEndpoint.addCashier(request));
    }
}
