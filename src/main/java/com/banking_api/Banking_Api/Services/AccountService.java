package com.banking_api.Banking_Api.Services;

import com.banking_api.Banking_Api.Entities.Account;
import com.banking_api.Banking_Api.Repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Fetch all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Fetch a specific account by ID
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    // Fetch all accounts for a specific customer
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    // Add a new account
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with ID: " + id);
        }
        accountRepository.deleteById(id);
    }
    public Account deposit(Long accountId, Double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be greater than zero.");
        }
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        account.setBalance(account.getBalance() + amount); // Update balance
        return accountRepository.save(account);
    }


    public Account withdraw(Long accountId, Double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be greater than zero.");
        }
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds.");
        }

        account.setBalance(account.getBalance() - amount);
        System.out.println("ok");//
        return accountRepository.save(account); // Save changes
    }

}
