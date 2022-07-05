package com.example.testkb.endpoint.impl;

import com.example.testkb.config.security.CurrentUser;
import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.dto.response.TransferReport;
import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.endpoint.TransferEndpoint;
import com.example.testkb.entity.Account;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transfer;
import com.example.testkb.entity.User;
import com.example.testkb.entity.enums.TransferStatus;
import com.example.testkb.mapper.TransferMapper;
import com.example.testkb.service.*;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferEndpointImpl implements TransferEndpoint {

    private final BankService bankService;
    private final TransferService transferService;
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final UserService userService;
    private final TransferMapper transferMapper;

    private final BigDecimal commission = new BigDecimal("0.01");

    public TransferEndpointImpl(BankService bankService,
                                TransferService transferService,
                                TransactionService transactionService,
                                AccountService accountService,
                                UserService userService,
                                TransferMapper transferMapper) {
        this.bankService = bankService;
        this.transferService = transferService;
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.userService = userService;
        this.transferMapper = transferMapper;
    }

    @Override
    @Transactional
    public TransferResponse transfer(@NonNull TransferMoneyRequest request,
                                     @CurrentUser UserPrincipal currentUser) {
        Bank receiverBank = bankService.getById(request.getReceiverBankId());
        User cashier = userService.getById(currentUser.getId());

        BigDecimal commission = calculateCommission(request.getSum());
        Transfer transfer = transferService.create(request, cashier, receiverBank, commission);
        Account account = accountService.getByBankAndCurrency(receiverBank, request.getCurrency());
        BigDecimal startAccountBalance = account.getBalance();

        accountService.replenish(receiverBank, request.getCurrency(), request.getSum());
        transactionService.transfer(account, startAccountBalance, request.getSum());

        return transferMapper.toTransferResponse(transfer);
    }

    @Override
    @Transactional
    public TransferResponse getTransfer(@NonNull TransferGetRequest request,
                                        @CurrentUser UserPrincipal currentUser) {
        Transfer transfer = transferService.getActive(TransferStatus.NOT_CASHED, request.getTransferCode(), request.getReceiverINN());
        Bank bank = transfer.getReceiverBank();
        Account account = accountService.getByBankAndCurrency(bank, transfer.getCurrency());
        BigDecimal startAccountBalance = account.getBalance();

        accountService.debit(bank, transfer.getCurrency(), transfer.getSum());
        transfer.setStatus(TransferStatus.CASHED);
        transferService.save(transfer);
        transactionService.cashOutTransfer(account, startAccountBalance, transfer.getSum());

        return transferMapper.toTransferResponse(transfer);
    }

    @Override
    public TransferReport getReport(@CurrentUser UserPrincipal currentUser) {
        User cashier = userService.getById(currentUser.getId());
        Bank bank = bankService.getById(cashier.getBank().getId());
        TransferReport report = new TransferReport();
        report.setProfit(transferService.getTotalSumOfCommissions(bank));
        report.setTransfer(transferService.getTotalSumOfTransfers(bank));

        return report;
    }

    private BigDecimal calculateCommission(BigDecimal sum) {
        return sum.multiply(commission);
    }
}
