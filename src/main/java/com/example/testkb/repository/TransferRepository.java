package com.example.testkb.repository;

import com.example.testkb.entity.Transaction;
import com.example.testkb.entity.enums.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transaction, Long> {
    Transaction getByStatusAndCode(TransferStatus status, String code);
}
