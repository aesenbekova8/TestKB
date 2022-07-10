package com.example.testkb.endpoint;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.CashOutTransferResponse;
import com.example.testkb.dto.response.TransferReport;
import com.example.testkb.dto.response.TransferResponse;

import java.util.List;

public interface TransferEndpoint {
    TransferResponse transfer(TransferMoneyRequest request, @CurrentUser UserPrincipal currentUser);
    CashOutTransferResponse getTransfer(TransferGetRequest request, @CurrentUser UserPrincipal currentUser);
    TransferReport getReport(@CurrentUser UserPrincipal currentUser);
    List<TransferResponse> getAllActiveByReceiverBank(Long receiverBankId);
}
