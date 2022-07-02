package com.example.testkb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cash_desk")
public class CashDesk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "cashDesk", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Shift> shifts = new HashSet<>();
}
