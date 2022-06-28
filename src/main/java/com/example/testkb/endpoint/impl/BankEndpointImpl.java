package com.example.testkb.endpoint.impl;

import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.endpoint.BankEndpoint;
import com.example.testkb.entity.Transaction;
import com.example.testkb.entity.enums.TransferStatus;
import com.example.testkb.service.BankService;
import com.example.testkb.service.TransferService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankEndpointImpl implements BankEndpoint {

    private final BankService bankService;
    private final TransferService transferService;

    public BankEndpointImpl(BankService bankService,
                            TransferService transferService) {
        this.bankService = bankService;
        this.transferService = transferService;
    }

    @Override
    @Transactional
    public void getTransfer(@NonNull TransferGetRequest request) {
        Transaction transaction = transferService.getActive(TransferStatus.ACTIVE, request.getTransferCode());
        bankService.cashOutTransfer(transaction);
        transaction.setStatus(TransferStatus.CASHED_OUT);
        transferService.save(transaction);
    }
}
