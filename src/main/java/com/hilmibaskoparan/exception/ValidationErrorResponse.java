package com.hilmibaskoparan.exception;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationErrorResponse extends BaseError {


    Map<String, String> validationErrors;
}
