package com.example.librarymanagement.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@RequestMapping("/api/library")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/borrow")
    public ResponseEntity<Transaction> borrowBook(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.borrowBook(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByBook(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long bookId) {
        List<Transaction> transactions = transactionService.getAllTransactions(userId,bookId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

}
