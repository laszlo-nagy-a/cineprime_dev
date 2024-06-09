package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.models.Writer;
import com.homeproject.cineprime.services.ControllerExceptionHandler;
import com.homeproject.cineprime.services.WriterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WriterController {

    private WriterService writerService;

    public WriterController (WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping("/writers")
    public List<Writer> findAllWriter() {
        return writerService.getAllWriter();
    }

    @GetMapping("/writers/{writer-id}")
    public Optional<Writer> findWriterById(@PathVariable("writer-id")Long id) {
        return writerService.getWriterById(id);
    }

    @PostMapping("/writers")
    public Writer createWriter(@Valid @RequestBody Writer writer) {
        return writerService.createWriter(writer);
    }

    @PutMapping("/writers")
    public Writer updateWriter(@Valid @RequestBody Writer writerToUpdate) {
        return writerService.updateWriter(writerToUpdate);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
