package com.hilmibaskoparan.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseError {

    private String message;
    private Integer statusCode;
    private String statusName;
    private String path;
    private String method;
    private LocalDateTime timestamp;

}