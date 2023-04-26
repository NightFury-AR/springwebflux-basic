package com.reactivelearning.controller;

import com.reactivelearning.dto.PurchaseOrderRequestDto;
import com.reactivelearning.dto.PurchaseOrderResponseDto;
import com.reactivelearning.service.OrderFulfillmentService;
import com.reactivelearning.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {

    @Autowired
    private OrderFulfillmentService orderFulfillmentService;

    @Autowired
    private OrderQueryService queryService;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono){
        return this.orderFulfillmentService.processOrder(requestDtoMono)
                                .map(ResponseEntity::ok)
                                .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
                                .onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }

    @GetMapping("user/{userId}")
    public Flux<PurchaseOrderResponseDto> getOrdersByUserId(@PathVariable int userId){
        return this.queryService.getProductsByUserId(userId);
    }

}
