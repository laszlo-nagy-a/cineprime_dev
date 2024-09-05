package com.homeproject.cineprime.logic.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class NullCheckUtil {

    public static void checkIfNull(Optional<?> optional) {
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: ");
        }
    }
}
