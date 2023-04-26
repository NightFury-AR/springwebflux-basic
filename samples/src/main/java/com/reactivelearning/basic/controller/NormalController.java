package com.reactivelearning.basic.controller;

import com.reactivelearning.basic.model.Customer;
import com.reactivelearning.basic.service.NormalCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@RestController
//@RequestMapping("/normal/customer")
public class NormalController {

    @Autowired private NormalCustomerService service;

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
        return this.service.getCustomerById(customerId);
    }

    @GetMapping("/list")
    public List<Customer> getCustomerList() {
        return this.service.getCustomerList();
    }
}
