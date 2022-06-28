package com.example.testkb.endpoint.impl;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.endpoint.TransferEndpoint;
import com.example.testkb.mapper.TransferMapper;
import com.example.testkb.service.TransferService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferEndpointImpl implements TransferEndpoint {

    private final TransferService transferService;
    private final TransferMapper transferMapper;

    public TransferEndpointImpl(TransferService transferService,
                                TransferMapper transferMapper) {
        this.transferService = transferService;
        this.transferMapper = transferMapper;
    }

    @Override
    @Transactional
    public TransferResponse transfer(@NonNull TransferMoneyRequest request) {
        return transferMapper.toTransferResponse(transferService.transfer(request));
    }
}
