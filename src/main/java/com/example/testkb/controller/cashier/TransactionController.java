package com.example.testkb.controller.cashier;

import com.example.testkb.dto.response.TransactionResponse;
import com.example.testkb.endpoint.TransactionEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Transaction")
@RestController
@RequestMapping("/api/kb/cashier/transactions")
public class TransactionController {

    private final TransactionEndpoint transactionEndpoint;

    public TransactionController(TransactionEndpoint transactionEndpoint) {
        this.transactionEndpoint = transactionEndpoint;
    }

    @ApiOperation(value = "Returns all transactions by Bank Id")
    @PostMapping("/{bankId}")
    public ResponseEntity<List<TransactionResponse>> getAllByBank(@PathVariable Long bankId) {
        return ResponseEntity.ok(transactionEndpoint.getAllByBankId(bankId));
    }
}
