package com.example.testkb.controller.general;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/kb/profile")
public class UserController {

    private final UserEndpoint userEndpoint;

    public UserController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @PutMapping("/update-password")
    public ResponseEntity<UserView> changePassword(@Valid @RequestBody PasswordUpdateRequest request,
                                                   @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(userEndpoint.updatePassword(request, currentUser));
    }
}
