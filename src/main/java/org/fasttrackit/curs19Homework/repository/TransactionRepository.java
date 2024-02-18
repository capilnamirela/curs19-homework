package org.fasttrackit.curs19Homework.repository;

import org.fasttrackit.curs19Homework.model.Transaction;
import org.fasttrackit.curs19Homework.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getAllByType(TransactionType type);

    List<Transaction> getByAmountGreaterThan(Double minAmount);

    List<Transaction> getByAmountLessThan(Double maxAmount);

    List<Transaction> getAllByTypeAndAmountGreaterThan(TransactionType type, Double minAmount);

    List<Transaction> getAllByTypeAndAmountLessThan(TransactionType type, Double maxAmount);

    List<Transaction> getAllByAmountBetween(Double minAmount, Double maxAmount);

    List<Transaction> getByTypeAndAmountBetween(TransactionType type, Double minAmount, Double maxAmount);

    @Query(value = "SELECT TYPE, SUM(AMOUNT) FROM TRANSACTION GROUP BY TYPE", nativeQuery = true)
    List<Map<TransactionType, Double>> getSumAmountGroupedByType();

    @Query(value = "SELECT PRODUCT, SUM(AMOUNT) FROM TRANSACTION GROUP BY PRODUCT", nativeQuery = true)
    List<Map<String, Double>> getSumAmountGroupedByProduct();
}

