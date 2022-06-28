package com.example.testkb.controller.cashier;

import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.endpoint.BankEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashier/bank")
public class BankController {

    private final BankEndpoint bankEndpoint;

    public BankController(BankEndpoint bankEndpoint) {
        this.bankEndpoint = bankEndpoint;
    }

    @PostMapping("/cash-out-transfer")
    public void cashOutTransfer(@RequestBody TransferGetRequest request) {
        bankEndpoint.getTransfer(request);
    }
}
