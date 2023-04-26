package com.reactivelearning.dto;

import com.reactivelearning.entity.ProductEntity;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.*;

@Component
public class ProductMapper {

    public ProductEntity toEntity(Product dto) {
        ProductEntity entity = new ProductEntity();
        copyProperties(dto,entity);
        return entity;
    }

    public Product toDto(ProductEntity entity) {
        Product dto = new Product();
        copyProperties(entity,dto);
        return dto;
    }

}
