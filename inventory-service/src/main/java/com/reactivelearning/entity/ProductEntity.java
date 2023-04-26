package com.reactivelearning.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class ProductEntity {
        @Id
        private String productId;
        private String productName;
        private Long productPrice;
        private String productDescription;
}
