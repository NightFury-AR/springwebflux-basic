package com.reactivelearning.basic.exception;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class AppError {
    private String status;
    private String source;
    private String errorMessage;
    private ZonedDateTime timeStamp;
}
