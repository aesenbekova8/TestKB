package com.example.testkb.endpoint;

import com.example.testkb.dto.response.AccountResponse;

import java.util.List;

public interface AccountEndpoint {
    List<AccountResponse> getAllByBank(Long bankId);
}
