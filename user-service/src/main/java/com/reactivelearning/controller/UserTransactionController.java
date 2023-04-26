package com.reactivelearning.controller;

import com.reactivelearning.dto.TransactionRequestDto;
import com.reactivelearning.dto.TransactionResponseDto;
import com.reactivelearning.entity.UserTransaction;
import com.reactivelearning.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
public class UserTransactionController {

    @Autowired private UserTransactionService service;

    @GetMapping("/{transactionId}")
    public Mono<UserTransaction> getUserTransactionById(@PathVariable("transactionId") Integer transactionId) {
        return this.service
                .getTransactionById(transactionId);
    }

    @GetMapping("/user/{userId}")
    public Flux<UserTransaction> getUserTransactionByUserId(@PathVariable("userId") Integer userId) {
        return this.service
                .getTransactionsByUserId(userId);
    }

    @PostMapping("/post")
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> newTransaction) {
        return newTransaction
                .flatMap(this.service::createTransaction);
    }

}

