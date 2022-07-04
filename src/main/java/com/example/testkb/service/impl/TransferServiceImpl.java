package com.example.testkb.service.impl;

import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transfer;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.TransferStatus;
import com.example.testkb.exception.LogicException;
import com.example.testkb.repository.TransferRepository;
import com.example.testkb.service.TransferService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    @Transactional
    public Transfer save(@NonNull Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    @Transactional
    public Transfer create(@NonNull TransferMoneyRequest request,
                           @NonNull User cashier,
                           @NonNull Bank receiverBank) {

        Transfer transfer = new Transfer();
        transfer.setSenderINN(request.getSenderINN());
        transfer.setReceiverINN(request.getReceiverINN());
        transfer.setCurrency(request.getCurrency());
        transfer.setSum(request.getSum());
        transfer.generateCode();
        transfer.setCashier(cashier);
        transfer.setSenderBank(cashier.getBank());
        transfer.setReceiverBank(receiverBank);

        return transferRepository.save(transfer);
    }

    @Override
    public Transfer getActive(@NonNull TransferStatus status,
                              @NonNull String code,
                              @NonNull String receiverINN) {
        return Optional.of(transferRepository.findByStatusAndCodeAndReceiverINN(status, code, receiverINN))
                .orElseThrow(() -> new LogicException(String.format("There are no active transfers with code: %s", code)));
    }
}
