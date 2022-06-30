package com.example.testkb.controller.cashier;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.endpoint.TransferEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/kb/cashier/transfer")
public class TransferControllerCashier {

    private final TransferEndpoint transferEndpoint;

    public TransferControllerCashier(TransferEndpoint transferEndpoint) {
        this.transferEndpoint = transferEndpoint;
    }

    @PostMapping("/")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferMoneyRequest request,
                                                     @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(transferEndpoint.transfer(request, currentUser));
    }

    @PostMapping("/cash-out-transfer")
    public void cashOutTransfer(@RequestBody TransferGetRequest request,
                                @CurrentUser UserPrincipal currentUSer) {
        transferEndpoint.getTransfer(request, currentUSer);
    }
}
