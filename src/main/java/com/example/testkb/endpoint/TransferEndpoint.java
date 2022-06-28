package com.example.testkb.endpoint;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.TransferResponse;

public interface TransferEndpoint {
    TransferResponse transfer(TransferMoneyRequest request);
}
