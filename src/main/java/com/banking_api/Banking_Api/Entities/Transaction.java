package com.banking_api.Banking_Api.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // e.g., DEPOSIT, WITHDRAWAL

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction() {}

    public Transaction(String type, Double amount, LocalDateTime timestamp, Account account) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.account = account;
    }
}
