package com.example.testkb.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TransferGetRequest {
    @NotBlank
    private String receiverINN;
    @NotBlank
    private String transferCode;
}
