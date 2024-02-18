package org.fasttrackit.curs19Homework;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.curs19Homework.model.Transaction;
import org.fasttrackit.curs19Homework.model.TransactionType;
import org.fasttrackit.curs19Homework.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static org.fasttrackit.curs19Homework.model.Transaction.*;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {
    private final TransactionRepository repository;


    @Override
    public void run(String... args) throws Exception {
        this.repository.save(builder()
                        .product("dress")
                        .type(TransactionType.SELL)
                        .amount(250.00)
                        .build());
        this.repository.save(builder()
                        .product("shoes")
                        .type(TransactionType.SELL)
                        .amount(350.50)
                        .build());
        this.repository.save(builder()
                        .product("dress")
                        .type(TransactionType.BUY)
                        .amount(75.00)
                        .build());
        this.repository.save(builder()
                        .product("jacket")
                        .type(TransactionType.SELL)
                        .amount(475.00)
                        .build());
        this.repository.save(builder()
                        .product("jacket")
                        .type(TransactionType.BUY)
                        .amount(175.5)
                        .build());
    }
}
