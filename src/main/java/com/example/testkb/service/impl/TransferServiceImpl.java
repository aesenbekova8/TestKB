package com.example.testkb.service.impl;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transaction;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.TransferStatus;
import com.example.testkb.exception.LogicException;
import com.example.testkb.repository.TransferRepository;
import com.example.testkb.service.BankService;
import com.example.testkb.service.TransferService;
import com.example.testkb.service.UserService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final BankService bankService;
    private final UserService userService;

    public TransferServiceImpl(TransferRepository transferRepository,
                               BankService bankService,
                               UserService userService) {
        this.transferRepository = transferRepository;
        this.bankService = bankService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Transaction save(@NonNull Transaction transaction) {
        return transferRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction transfer(@NonNull TransferMoneyRequest request) {

        Bank receiverBank = bankService.getById(request.getReceiverBankId());
        User cashier = userService.getById(request.getCashierId());

        Transaction transaction = new Transaction();
        transaction.setSenderINN(request.getSenderINN());
        transaction.setReceiverINN(request.getReceiverINN());
        transaction.setCurrency(request.getCurrency());
        transaction.setSum(request.getSum());
        transaction.generateCode();
        transaction.setCashier(cashier);
        transaction.setSenderBank(cashier.getBank());
        transaction.setReceiverBank(receiverBank);

        bankService.transferMoney(receiverBank, request.getCurrency(), request.getSum());

        return transferRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction getActive(@NonNull TransferStatus status,
                                 @NonNull String code) {
        return Optional.of(transferRepository.getByStatusAndCode(status, code))
                .orElseThrow(() -> new LogicException(String.format("There are no active transfers with code: %s", code)));
    }
}
