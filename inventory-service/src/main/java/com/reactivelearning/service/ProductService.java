package com.reactivelearning.service;

import com.reactivelearning.dto.Product;
import com.reactivelearning.dto.ProductMapper;
import com.reactivelearning.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired private ProductMapper mapper;

   // @Autowired
    //private Sinks.Many<Product> sink;

    public Mono<Product> getProductById(String productId) {
        return this.productRepository
                .findById(productId)
                .map(mapper::toDto);
    }

    public Flux<Product> getProductList() {
        return this.productRepository
                .findAll()
                .map(mapper::toDto);
    }

    public Mono<Product> addNewProduct(Mono<Product> product) {
        return product
                .map(mapper::toEntity)
                .flatMap(this.productRepository::insert)
                .map(mapper::toDto);
    }

    public Mono<Product> updateProduct(String productId, Mono<Product> productDetails) {
        return this.productRepository
                .findById(productId)
                .flatMap(entity -> productDetails
                        .map(mapper::toEntity)
                        .doOnNext(e -> e.setProductId(productId))
                )
                .flatMap(this.productRepository::save)
                .map(mapper::toDto);
    }

    public Mono<Void> deleteProductById(String productId) {
        return this.productRepository
                .deleteById(productId);
    }

}
