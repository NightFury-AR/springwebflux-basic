package com.reactivelearning.service;

import com.reactivelearning.dto.TransactionRequestDto;
import com.reactivelearning.dto.TransactionResponseDto;
import com.reactivelearning.dto.TransactionStatus;
import com.reactivelearning.entity.UserTransaction;
import com.reactivelearning.mapper.UserTransactionMapper;
import com.reactivelearning.repository.UserRepository;
import com.reactivelearning.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserTransactionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTransactionRepository repository;
    @Autowired
    private UserTransactionMapper mapper;

    public Mono<UserTransaction> getTransactionById(Integer transactionId) {
        return this.repository
                .findById(transactionId);
    }

    public Flux<UserTransaction> getTransactionsByUserId(Integer userId) {
        return this.repository
                .findByUserId(userId);
    }

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto) {
        return this.userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> mapper.toEntity(requestDto))
                .flatMap(this.repository::save)
                .map(ut -> mapper.toDTO(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(mapper.toDTO(requestDto, TransactionStatus.DECLINED));
    }

}
