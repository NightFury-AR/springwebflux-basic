package com.reactivelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private String productId;
    private String productName;
    private Long productPrice;
    private String productDescription;
}
