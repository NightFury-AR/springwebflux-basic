package com.reactivelearning.basic.functionalendpoints.config;

import com.reactivelearning.basic.functionalendpoints.handler.CustomerHandler;
import com.reactivelearning.basic.exception.AppException;
import com.reactivelearning.basic.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouteConfig {

    @Autowired private CustomerHandler handler;

    @Bean
    public RouterFunction<ServerResponse> appRoute() {
        return RouterFunctions.route()
                .path("api/customer",this::customerRoute)
                .build();
    }
    private RouterFunction<ServerResponse> customerRoute() {
        return RouterFunctions.route()
                .POST("/add",this.handler::addNewCustomer)
                .GET("/list",this.handler::getCustomerList)
                .GET("/{customerId}",this.handler::getCustomerById)
                .onError(AppException.class,(e, serverRequest) -> ExceptionUtils.handleAppException(e))
                .build();
    }
}
