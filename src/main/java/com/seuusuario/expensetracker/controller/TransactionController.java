package com.seuusuario.expensetracker.controller;

import com.seuusuario.expensetracker.entity.Transaction;
import com.seuusuario.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/expenses-by-category")
    public ResponseEntity<Map<String, BigDecimal>> getExpensesByCategory() {
        Map<String, BigDecimal> expensesByCategory = transactionService.getExpensesByCategory();
        return ResponseEntity.ok(expensesByCategory);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.findById(id)
                .map(transaction -> {
                    transaction.setType(transactionDetails.getType());
                    transaction.setAmount(transactionDetails.getAmount());
                    transaction.setDate(transactionDetails.getDate());
                    transaction.setCategory(transactionDetails.getCategory());
                    Transaction updatedTransaction = transactionService.save(transaction);
                    return ResponseEntity.ok(updatedTransaction);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id) {
        return transactionService.findById(id)
                .map(transaction -> {
                    transactionService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());

    }
}













