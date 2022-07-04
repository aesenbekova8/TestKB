package com.example.testkb.entity;

import com.example.testkb.entity.enums.Currency;
import com.example.testkb.entity.enums.TransferStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "sender_inn")
    private String senderINN;

    @NotBlank
    @Column(name = "receiver_inn")
    private String receiverINN;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @NotBlank
    @Column(name = "sum")
    private BigDecimal sum = BigDecimal.ZERO;

    @NotBlank
    @Column(name = "commission")
    private BigDecimal commission = new BigDecimal(0);

    @NotBlank
    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private User cashier;

    @ManyToOne
    @JoinColumn(name = "sender_bank_id")
    private Bank senderBank;

    @ManyToOne
    @JoinColumn(name = "receiver_bank_id")
    private Bank receiverBank;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private TransferStatus status = TransferStatus.NOT_CASHED;

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    public void generateCode() {
        String code = String.valueOf(hashCode());
        setCode(code);
    }
}
