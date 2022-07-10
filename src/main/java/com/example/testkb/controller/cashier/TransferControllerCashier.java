package com.example.testkb.controller.cashier;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.CashOutTransferResponse;
import com.example.testkb.dto.response.TransferReport;
import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.endpoint.TransferEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Transfer")
@RestController
@RequestMapping("/api/kb/cashier/transfer")
public class TransferControllerCashier {

    private final TransferEndpoint transferEndpoint;

    public TransferControllerCashier(TransferEndpoint transferEndpoint) {
        this.transferEndpoint = transferEndpoint;
    }

    @ApiOperation(value = "Sends transfer from bank (branch of bank) " + "'a'" + " to bank " + "'b'")
    @PostMapping("/")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferMoneyRequest request,
                                                     @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(transferEndpoint.transfer(request, currentUser));
    }

    @ApiOperation(value = "Cash out from receiver bank (branch of bank)")
    @PostMapping("/cash-out-transfer")
    public ResponseEntity<CashOutTransferResponse> cashOutTransfer(@RequestBody TransferGetRequest request,
                                                                   @CurrentUser UserPrincipal currentUSer) {
        return ResponseEntity.ok(transferEndpoint.getTransfer(request, currentUSer));
    }

    @ApiOperation(value = "Gets a report at last 24 hours")
    @GetMapping("/report")
    public ResponseEntity<TransferReport> getReport(@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(transferEndpoint.getReport(currentUser));
    }

    @ApiOperation(value = "Returns all NOT CASHED transfers by receiver bank ID")
    @GetMapping("/{receiverBankId}")
    public ResponseEntity<List<TransferResponse>> getAllActualByReceiverBank(@PathVariable Long receiverBankId) {
        return ResponseEntity.ok(transferEndpoint.getAllActiveByReceiverBank(receiverBankId));
    }
}
