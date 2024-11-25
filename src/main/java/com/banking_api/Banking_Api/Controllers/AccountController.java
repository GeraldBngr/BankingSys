package com.banking_api.Banking_Api.Controllers;



import com.banking_api.Banking_Api.Dtos.TransactionDTO;
import com.banking_api.Banking_Api.Entities.Account;
import com.banking_api.Banking_Api.Services.AccountService;
import com.banking_api.Banking_Api.Services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    // GET /accounts - Fetch all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // GET /accounts/{id} - Fetch a specific account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    // GET /customers/{customerId}/accounts - Fetch accounts for a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAccountsByCustomerId(customerId));
    }

    // POST /accounts - Add a new account
    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.addAccount(account));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account with ID " + id + " has been deleted.");
    }
    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestParam Double amount) {
        Account updatedAccount = accountService.deposit(id, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    // POST /accounts/{id}/withdraw - Withdraw money
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestParam Double amount) {
        Account updatedAccount = accountService.withdraw(id, amount);
        return ResponseEntity.ok(updatedAccount);
    }
    // GET /accounts/{id}/transactions
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(id));
    }
}
