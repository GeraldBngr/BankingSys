package com.banking_api.Banking_Api.Services;

import com.banking_api.Banking_Api.Dtos.TransactionDTO;
import com.banking_api.Banking_Api.Entities.Account;
import com.banking_api.Banking_Api.Entities.Transaction;
import com.banking_api.Banking_Api.Repositories.AccountRepository;
import com.banking_api.Banking_Api.Repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // Fetch all transactions for a specific account
   /* public List<Transaction> getTransactionsByAccountId(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }
        return transactionRepository.findByAccountId(accountId);
    }
    */


    // Add a new transaction (Deposit or Withdrawal)
    public Transaction addTransaction(Long accountId, String type, Double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Transaction amount must be greater than zero.");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        if (type.equalsIgnoreCase("WITHDRAWAL") && account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds.");
        }

        // Update account balance
        if (type.equalsIgnoreCase("DEPOSIT")) {
            account.setBalance(account.getBalance() + amount);
        } else if (type.equalsIgnoreCase("WITHDRAWAL")) {
            account.setBalance(account.getBalance() - amount);
        }

        accountRepository.save(account);

        // Create and save the transaction
        Transaction transaction = new Transaction(type, amount, LocalDateTime.now(), account);
        return transactionRepository.save(transaction);
    }
    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }

        return transactionRepository.findByAccountId(accountId).stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getId(),
                        transaction.getTimestamp(),
                        transaction.getType(),
                        transaction.getAmount()
                ))
                .collect(Collectors.toList());
    }



}
