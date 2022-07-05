package com.example.testkb.controller.admin;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Admin")
@RestController
@RequestMapping(path = "/api/kb/admin")
public class UserControllerAdmin {

    private final UserEndpoint userEndpoint;

    public UserControllerAdmin(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

    })

    @ApiOperation(value = "Adds a new Cashier to the system")
    @PostMapping("/create-cashier")
    public ResponseEntity<UserView> createCashier(@Valid @RequestBody UserCreateRequest request,
                                                  @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(userEndpoint.addCashier(request,currentUser ));
    }
}
