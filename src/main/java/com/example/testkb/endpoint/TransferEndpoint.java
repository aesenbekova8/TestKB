package com.example.testkb.endpoint;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.TransferResponse;

public interface TransferEndpoint {
    TransferResponse transfer(TransferMoneyRequest request, @CurrentUser UserPrincipal currentUser);
    void getTransfer(TransferGetRequest request, @CurrentUser UserPrincipal currentUser);
}
