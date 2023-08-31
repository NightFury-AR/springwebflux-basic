package com.reactivelearning.basic.service;
import com.reactivelearning.basic.model.Customer;
//import com.reactivelearning.springwebflux.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NormalCustomerService {
   /* private final List<Customer> customerData;

    public NormalCustomerService() {
        this.customerData = Util.loadData();
    }

    public Customer getCustomerById(Long customerId) {
        return this.customerData.stream()
                .filter(customer -> customerId.equals(customer.getCustomerId()))
                .peek(customer -> Util.freeze(1))
                .peek(System.out::println)
                .findFirst().orElseThrow(() -> new RuntimeException("invalid"));
    }

    public List<Customer> getCustomerList() {
        return this.customerData.stream()
                .peek(customer -> Util.freeze(1))
                .peek(customer -> System.out.println("fetched - "+customer))
                .collect(Collectors.toList());
    }

*/

}
