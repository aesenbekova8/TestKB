package com.example.testkb.endpoint;

import com.example.testkb.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionEndpoint {
    List<TransactionResponse> getAllByBankId(Long bankId);
}
