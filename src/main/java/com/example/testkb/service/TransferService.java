package com.example.testkb.service;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transfer;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.Currency;
import com.example.testkb.entity.enums.TransferStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TransferService {
    Transfer save(Transfer transfer);
    Transfer create(TransferMoneyRequest request, User cashier, Bank receiverBank, BigDecimal commission);
    Transfer getActive(TransferStatus status, String code, String receiverINN);
    Map<Currency, BigDecimal> getTotalSumOfCommissions(Bank bank);
    Map<Currency, BigDecimal> getTotalSumOfTransfers(Bank bank);
    List<Transfer> getAllByStatusAndByReceiverBankId(TransferStatus status, Long bankId);
}
