package com.example.testkb.controller.cashier;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.endpoint.TransferEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cashier/transfer")
public class TransferController {

    private final TransferEndpoint transferEndpoint;

    public TransferController(TransferEndpoint transferEndpoint) {
        this.transferEndpoint = transferEndpoint;
    }

    @PostMapping("/create")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferMoneyRequest request) {
        return ResponseEntity.ok(transferEndpoint.transfer(request));
    }
}
