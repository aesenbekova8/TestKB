package com.example.testkb.service;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transfer;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.TransferStatus;

public interface TransferService {
    Transfer save(Transfer transfer);
    Transfer create(TransferMoneyRequest request, User cashier, Bank receiverBank);
    Transfer getActive(TransferStatus status, String code);
}
