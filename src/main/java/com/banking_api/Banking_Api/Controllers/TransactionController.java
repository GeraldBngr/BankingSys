package com.banking_api.Banking_Api.Controllers;

import com.banking_api.Banking_Api.Dtos.TransactionDTO;
import com.banking_api.Banking_Api.Entities.Transaction;
import com.banking_api.Banking_Api.Services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // GET /transactions/account/{accountId} - Fetch all transactions for an account
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
    }

    // POST /transactions/{accountId} - Add a new transaction
    @PostMapping("/{accountId}")
    public ResponseEntity<Transaction> addTransaction(
            @PathVariable Long accountId,
            @RequestParam String type,
            @RequestParam Double amount
    ) {
        return ResponseEntity.ok(transactionService.addTransaction(accountId, type, amount));
    }
}
