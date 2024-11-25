package com.banking_api.Banking_Api.Dtos;

import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;
    private LocalDateTime timestamp;
    private String type;
    private Double amount;

    public TransactionDTO(Long id, LocalDateTime timestamp, String type, Double amount) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    // Getters and setters
}
