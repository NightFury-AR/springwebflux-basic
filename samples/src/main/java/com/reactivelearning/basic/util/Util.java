package com.reactivelearning.basic.util;

import com.github.javafaker.Faker;
import com.reactivelearning.basic.model.Customer;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class Util {


    private static final Faker faker = Faker.instance();

    public static void freeze(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Customer> loadData() {
        return IntStream.rangeClosed(1,10)
                .asLongStream()
                .mapToObj(id -> {
                    return Customer.builder()
                            .customerId(id)
                            .customerName(Util.faker.name().fullName())
                            .customerEmail(Util.faker.internet().emailAddress())
                            .customerPhone(Util.faker.phoneNumber().cellPhone())
                            .build();
                }).toList();
    }

    public static Flux<Customer> loadFlux() {
        return Flux.fromIterable(loadData());
    }
}
