package com.reactivelearning.basic.controller;
import com.reactivelearning.basic.model.Customer;
import com.reactivelearning.basic.service.WebFluxCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

//@RestController
//@RequestMapping("/flux/customer")
public class WebFluxController {

    @Autowired
    WebFluxCustomerService service;

    @GetMapping("/{customerId}")
    public Mono<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        return this.service.getCustomerById(customerId);
    }

    @GetMapping(value = "/list",produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomerList() {
        return this.service.getCustomerList();
    }

    //without stream
    @GetMapping(value = "/list/wos")
    public Flux<Customer> getCustomerListWithOutStream() {
        return this.service.getCustomerList();
    }

}
