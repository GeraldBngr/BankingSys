package com.banking_api.Banking_Api.Repositories;

import com.banking_api.Banking_Api.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
}
