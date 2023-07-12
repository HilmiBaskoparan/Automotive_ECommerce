package com.hilmibaskoparan.error;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomErrorHandleWebRequest implements ErrorController {

    // We customize the error coming from the SpringBoot default.

    private final ErrorAttributes errorAttributes;

    // http://localhost:3000/error
    // To catch /error from Spring and make a custom handle
    @RequestMapping("/error")
    public ApiResult handleError(WebRequest webRequest) {
        // Attend ApiResult variables
        int status;
        String message, path;
        ApiResult apiResult;

        // After Spring 2.3
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(
                webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS)
        );

        status = (Integer) attributes.get("status");
        message = (String) attributes.get("message");
        path = (String) attributes.get("path");
        apiResult = new ApiResult(status, path, message);

        // if apiResult exist in Attributes
        if (attributes.containsKey("errors")) {
            List<FieldError> fieldErrorList = (List) attributes.get("errors");
            Map<String, String> validationMistake = new HashMap<>();
            for (FieldError fieldError : fieldErrorList) {
                validationMistake.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            apiResult.setValidationErrors(validationMistake);
        }
        return apiResult;
    }
}
