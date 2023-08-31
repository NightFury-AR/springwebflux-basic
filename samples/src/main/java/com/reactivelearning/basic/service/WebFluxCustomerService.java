package com.reactivelearning.basic.service;

import com.reactivelearning.basic.model.Customer;
//import com.reactivelearning.springwebflux.util.Util;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class WebFluxCustomerService {
    private Flux<Customer> customerData = null;

    public WebFluxCustomerService() {
        this.customerData = null;
    }

    public Mono<Customer> getCustomerById(Long customerId) {
        return this.customerData
                //.delayElements(Duration.ofSeconds(1))
                .filter(customer -> customerId.equals(customer.getCustomerId()))
                .doOnComplete(() -> System.out.println("fetched successfully"))
                .next();
    }

    public Flux<Customer> getCustomerList() {
        return this.customerData
                .doOnNext(customer -> System.out.println("fetching "+ customer))
                .doOnComplete(() -> System.out.println("completed"));
    }

    public Mono<Customer> addNewCustomer(Mono<Customer> customer) {
        AtomicReference<Long> c = new AtomicReference<>();
        this.customerData = customer.doOnNext(customer1 -> c.set(customer1.getCustomerId()))
                .mergeWith(this.customerData);
        return this.customerData
                .filter(cus -> cus.getCustomerId() == c.get())
                .next();

    }
}
