package com.example.testkb.repository;

import com.example.testkb.entity.Transfer;
import com.example.testkb.entity.enums.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Transfer findByStatusAndCode(TransferStatus status, String code);
}
