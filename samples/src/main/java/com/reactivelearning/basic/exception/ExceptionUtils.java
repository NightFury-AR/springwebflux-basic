package com.reactivelearning.basic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

public class ExceptionUtils {
    public static Mono<ServerResponse> handleAppException(AppException ex) {
        return ServerResponse
                .status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponse(ex),AppError.class);
    }

    public static AppError buildErrorResponse(AppException ex) {
        return AppError.builder()
                .source("INTERNAL")
                .errorMessage(ex.getMessage())
                .status("400")
                .timeStamp(ZonedDateTime.now())
                .build();
    }
}
