package com.reactivelearning.basic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Builder
public class Customer {
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
}
