package com.banking_api.Banking_Api.Dtos;

public class AccountSummaryDTO {
    private Long id;
    private Double balance;

    public AccountSummaryDTO(Long id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

// Getters and setters
}
