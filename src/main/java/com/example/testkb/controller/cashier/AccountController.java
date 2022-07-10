package com.example.testkb.controller.cashier;

import com.example.testkb.dto.response.AccountResponse;
import com.example.testkb.endpoint.AccountEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Account")
@RestController
@RequestMapping("/api/kb/cashier/account")
public class AccountController {

    private  final AccountEndpoint accountEndpoint;

    public AccountController(AccountEndpoint accountEndpoint) {
        this.accountEndpoint = accountEndpoint;
    }

    @ApiOperation(value = "Returns accounts by Bank Id")
    @GetMapping("/{bankId}")
    public ResponseEntity<List<AccountResponse>> getAllByBank(@PathVariable Long bankId) {
        return ResponseEntity.ok(accountEndpoint.getAllByBank(bankId));
    }
}
