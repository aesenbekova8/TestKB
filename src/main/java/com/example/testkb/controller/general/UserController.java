package com.example.testkb.controller.general;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.PasswordUpdateRequest;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "User")
@RestController
@RequestMapping(path = "/api/kb/profile")
public class UserController {

    private final UserEndpoint userEndpoint;

    public UserController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @ApiOperation(value = "Updates password")
    @PutMapping("/update-password")
    public ResponseEntity<UserView> changePassword(@Valid @RequestBody PasswordUpdateRequest request,
                                                   @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(userEndpoint.updatePassword(request, currentUser));
    }
}
