package com.reactivelearning.basic.functionalendpoints.handler;

import com.reactivelearning.basic.exception.AppException;
import com.reactivelearning.basic.model.Customer;
import com.reactivelearning.basic.service.WebFluxCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired private WebFluxCustomerService service;

    public Mono<ServerResponse> getCustomerById(ServerRequest serverRequest) {
        long customerId = Long.parseLong(serverRequest.pathVariable("customerId"));
        Mono<Customer> customerById = this.service.getCustomerById(customerId);
        return  ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerById,Customer.class);
    }

    public Mono<ServerResponse> getCustomerList(ServerRequest serverRequest) {
        Flux<Customer> customerList = this.service.getCustomerList();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerList,Customer.class);
    }

    public Mono<ServerResponse> addNewCustomer(ServerRequest serverRequest) {
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        customerMono.doOnNext(payload -> {
            if (payload == null) {
                throw new AppException("Invalid payload");
            }
        });
        Mono<Customer> result = this.service.addNewCustomer(customerMono);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result,Customer.class);
    }
}
