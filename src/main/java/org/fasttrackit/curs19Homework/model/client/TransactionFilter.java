package org.fasttrackit.curs19Homework.model.client;

import org.fasttrackit.curs19Homework.model.TransactionType;

public record TransactionFilter(
        TransactionType type,
        Double minAmount,
        Double maxAmount

) {
}
