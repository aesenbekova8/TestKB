package com.example.testkb.dto.request;

import lombok.Data;

@Data
public class TransferGetRequest {
    private String receiverINN;
    private String transferCode;
}
