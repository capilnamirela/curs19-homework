package org.fasttrackit.curs19Homework.service;

import org.fasttrackit.curs19Homework.exceptions.TransactionNotFoundException;
import org.fasttrackit.curs19Homework.model.Transaction;
import org.fasttrackit.curs19Homework.model.TransactionType;
import org.fasttrackit.curs19Homework.model.client.TransactionFilter;
import org.fasttrackit.curs19Homework.repository.TransactionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions(TransactionFilter filter) {

        if (filter.type() != null && filter.minAmount() != null && filter.maxAmount() != null) {
            return repository.getByTypeAndAmountBetween(filter.type(), filter.minAmount(), filter.maxAmount());
        }

        if (filter.type() != null && filter.minAmount() != null) {
            return repository.getAllByTypeAndAmountGreaterThan(filter.type(), filter.minAmount());
        }

        if (filter.type() != null && filter.maxAmount() != null) {
            return repository.getAllByTypeAndAmountLessThan(filter.type(), filter.maxAmount());
        }

        if (filter.minAmount() != null && filter.maxAmount() != null) {
            return repository.getAllByAmountBetween(filter.minAmount(), filter.maxAmount());
        }

        if (filter.type() != null) {
            return repository.getAllByType(filter.type());
        }
        if (filter.minAmount() != null) {
            return repository.getByAmountGreaterThan(filter.minAmount());
        }
        if (filter.maxAmount() != null) {
            return repository.getByAmountLessThan(filter.maxAmount());
        }
        return repository.findAll();

    }

    public Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id %s was not found".formatted(id)));

    }

    public Transaction addTransaction(Transaction newTransaction) {
        return repository.save(newTransaction);
    }

    public Transaction replaceTransaction(Long id, Transaction replaceTransaction) {
        Transaction oldTransaction = getTransactionById(id);
        Transaction updatedTransaction = Transaction.builder()
                .id(oldTransaction.getId())
                .product(replaceTransaction.getProduct())
                .type(replaceTransaction.getType())
                .amount(replaceTransaction.getAmount())
                .build();
        return repository.save(updatedTransaction);
    }

    public Transaction deleteById(Long id) {
        Transaction transactionToBeDeleted = getTransactionById(id);
        repository.deleteById(id);
        return transactionToBeDeleted;
    }

    public List<Map<TransactionType, Double>> getTypeToSumAmount() {
        return repository.getSumAmountGroupedByType();
    }


    public List<Map<String, Double>> getProductToSumAmount() {
        return repository.getSumAmountGroupedByProduct();
    }

}
