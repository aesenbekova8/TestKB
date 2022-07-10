package com.example.testkb.controller.admin;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.UserCreateRequest;
import com.example.testkb.dto.response.UserView;
import com.example.testkb.endpoint.UserEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Admin")
@RestController
@RequestMapping(path = "/api/kb/admin")
public class UserControllerAdmin {

    private final UserEndpoint userEndpoint;

    public UserControllerAdmin(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @ApiOperation(value = "Adds a new Cashier to the system")
    @PostMapping("/create-cashier")
    public ResponseEntity<UserView> createCashier(@Valid @RequestBody UserCreateRequest request,
                                                  @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(userEndpoint.addCashier(request,currentUser ));
    }

    @ApiOperation(value = "Returns all bank cashiers")
    @GetMapping("/{bankId}")
    public ResponseEntity<List<UserView>> getCashiersByBank(@PathVariable Long bankId) {
        return ResponseEntity.ok(userEndpoint.getCashiersByBank(bankId));
    }
}
