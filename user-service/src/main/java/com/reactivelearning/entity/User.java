package com.reactivelearning.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@ToString
@Table(name = "account_user")
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
    private BigDecimal balance;
}
