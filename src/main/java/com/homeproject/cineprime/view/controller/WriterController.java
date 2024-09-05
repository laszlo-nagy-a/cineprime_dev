package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.domain.model.Writer;
import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.WriterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public void createWriter(@Valid @RequestBody Writer writer) {
        writerService.createWriter(writer);
    }

    @PutMapping("/writers")
    public void updateWriter(@Valid @RequestBody Writer writerToUpdate) {
        writerService.updateWriter(writerToUpdate);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
