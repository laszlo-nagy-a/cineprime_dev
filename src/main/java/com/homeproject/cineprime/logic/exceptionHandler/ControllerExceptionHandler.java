package com.homeproject.cineprime.logic.exceptionHandler;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


public class ControllerExceptionHandler {
    public static Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            System.out.println(error);
            String fieldName = ((FieldError) error).getField();
            System.out.println(error);
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    public static Map<String, String> handleNotFoundStatusExcetions(ResponseStatusException exception) {
        Map<String, String> exceptionDetails = new HashMap<>();

        exceptionDetails.put("error_message:",  exception.getReason() == null ? "no reason found, please try again" : exception.getReason());

        return exceptionDetails;
    }
}
