package com.example.testkb.repository;

import com.example.testkb.entity.Transfer;
import com.example.testkb.entity.enums.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Transfer findByStatusAndCodeAndReceiverINN(TransferStatus status, String code, String senderInn);

    List<Transfer> getAllBy();
}
