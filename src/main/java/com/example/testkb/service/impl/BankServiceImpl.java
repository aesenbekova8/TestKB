package com.example.testkb.service.impl;

import com.example.testkb.entity.Bank;
import com.example.testkb.exception.CustomEntityNotFoundException;
import com.example.testkb.repository.BankRepository;
import com.example.testkb.service.BankService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank getById(@NonNull Long id) {
        return Optional.of(bankRepository.getReferenceById(id))
                .orElseThrow(() -> new CustomEntityNotFoundException(format("Bank with id: %s ", id)));
    }
}
