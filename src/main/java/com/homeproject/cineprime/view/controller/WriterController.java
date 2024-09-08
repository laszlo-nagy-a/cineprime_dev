package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.WriterService;
import com.homeproject.cineprime.view.request_json.WriterRequestJson;
import com.homeproject.cineprime.view.response_json.WriterResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("writers")
public class WriterController {

    private WriterService writerService;

    public WriterController (WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping("/{writer-id}")
    public WriterResponseJson findWriter(@PathVariable("writer-id")String publicId) {
        return writerService.getWriterResponseJsonById(publicId);
    }
    @GetMapping
    public List<WriterResponseJson> findAllWriter() {
        return writerService.getAllWriterResponseJson();
    }

    @PostMapping
    public WriterResponseJson createWriter(@Valid @RequestBody WriterRequestJson request) {
        return writerService.createWriter(request);
    }

    @PutMapping
    public WriterResponseJson updateWriter(@Valid @RequestBody WriterRequestJson request) {
        return writerService.updateWriter(request);
    }

    @DeleteMapping("/{writer-id}")
    public String removeWriterById(@PathVariable("writer-id")String publicId) {
        return writerService.removeWriterByPublidId(publicId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }
}
