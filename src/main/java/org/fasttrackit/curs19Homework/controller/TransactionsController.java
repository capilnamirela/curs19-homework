package org.fasttrackit.curs19Homework.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.curs19Homework.model.Transaction;
import org.fasttrackit.curs19Homework.model.TransactionType;
import org.fasttrackit.curs19Homework.model.client.TransactionFilter;
import org.fasttrackit.curs19Homework.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionsController {
    private final TransactionService service;

    @GetMapping
    public List<Transaction> getAllTransactions(TransactionFilter filter) {
        return service.getAllTransactions(filter);
    }

    @GetMapping("{id}")
    public Transaction getTransactionWithId(@PathVariable Long id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction newTransaction) {
        return service.addTransaction(newTransaction);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable Long id, @RequestBody Transaction replaceTransaction) {
        return service.replaceTransaction(id, replaceTransaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteTransaction(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/amountGroupedByType")
    public List<Map<TransactionType, Double>> getSumAmountGroupedByType() {
        return service.getTypeToSumAmount();

    }
    @GetMapping("/amountGroupedByProduct")
    public List<Map<String, Double>> getSumAmountGroupedByProduct() {
        return service.getProductToSumAmount();
    }

}
