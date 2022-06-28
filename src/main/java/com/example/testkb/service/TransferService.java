package com.example.testkb.service;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.entity.Transaction;
import com.example.testkb.entity.enums.TransferStatus;

public interface TransferService {
    Transaction save(Transaction transaction);
    Transaction transfer(TransferMoneyRequest request);
    Transaction getActive(TransferStatus status, String code);
}
