package com.reactivelearning.mapper;

import com.reactivelearning.dto.TransactionRequestDto;
import com.reactivelearning.dto.TransactionResponseDto;
import com.reactivelearning.dto.TransactionStatus;
import com.reactivelearning.entity.UserTransaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class UserTransactionMapper {


    public TransactionResponseDto toDTO(TransactionRequestDto requestDto, TransactionStatus status) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setAmount(requestDto.getAmount());
        dto.setUserId(requestDto.getUserId());
        dto.setStatus(status);
        return dto;
    }

    public UserTransaction toEntity(TransactionRequestDto dto) {
        UserTransaction entity = new UserTransaction();
        entity.setUserId(dto.getUserId());
        entity.setAmount(dto.getAmount());
        entity.setTransactionDate(LocalDateTime.now());
        return entity;
    }



}
