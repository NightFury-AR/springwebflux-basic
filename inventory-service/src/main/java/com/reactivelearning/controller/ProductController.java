package com.reactivelearning.controller;

import com.reactivelearning.dto.Product;
import com.reactivelearning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired private ProductService service;

    @GetMapping
    public Flux<Product> getProductList() {
        return this.service
                .getProductList();
    }

    @PostMapping
    public Mono<Product> productPost(@RequestBody Mono<Product> productMono) {
        return this.service
                .addNewProduct(productMono);
    }

    @PutMapping("/{productId}")
    public Mono<Product> updateProduct(@PathVariable("productId") String productId, @RequestBody Mono<Product> productMono) {
        return this.service
                .updateProduct(productId,productMono);
    }

    @GetMapping("/{productId}")
    public Mono<Product> getProductById(@PathVariable("productId") String productId) {
        return this.service
                .getProductById(productId);
    }

    @DeleteMapping("/{productId}")
    public Mono<Void> deleteProductById(@PathVariable("productId") String productId) {
        return this.service
                .deleteProductById(productId);
    }

    @GetMapping("/check")
    public Mono<String> chkWebClient() {
        WebClient webClient = WebClient.builder().build();
        String url = "http:://www.google.com";
        String path = "/requests";
        String claim = "ZAXX";
        String vendor = "Non";
        return webClient
                .get()
                .uri(url + bulidURI(path,claim,vendor))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ClientResponse::createException)
                .onStatus(HttpStatusCode::is5xxServerError, ClientResponse::createException)
                .bodyToMono(String.class);
    }

    private Function<UriBuilder, URI> bulidURI(String path,String claim, String vendor) {
        return uriBuilder ->
                uriBuilder
                        .path(path)
                        .queryParamIfPresent("claim", Optional.ofNullable(claim))
                        .queryParamIfPresent("vendor",Optional.ofNullable(vendor))
                        .queryParam("fam","fame")
                        .build();
    }

}
