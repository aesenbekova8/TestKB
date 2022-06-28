package com.example.testkb.endpoint;

import com.example.testkb.dto.request.TransferGetRequest;

public interface BankEndpoint {
    void getTransfer(TransferGetRequest request);
}
