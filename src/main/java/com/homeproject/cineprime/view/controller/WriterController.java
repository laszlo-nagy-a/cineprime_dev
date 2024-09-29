package com.homeproject.cineprime.view.controller;

import com.homeproject.cineprime.logic.exceptionHandler.ControllerExceptionHandler;
import com.homeproject.cineprime.logic.service.WriterService;
import com.homeproject.cineprime.view.request_json.WriterRequestJson;
import com.homeproject.cineprime.view.response_json.WriterResponseJson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("writers")
public class WriterController {
    private final WriterService writerService;

    public WriterController (WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping("/{writer-id}")
    public WriterResponseJson findWriter(@PathVariable("writer-id")String publicId) {
        return writerService.getWriterResponseJsonById(publicId);
    }
    @GetMapping
    public List<WriterResponseJson> findAllWriter(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, name = "pagesize") Optional<Integer> pageSize,
            @RequestParam(required = false, name = "pagenumber") Optional<Integer> pageNumber
    ) {
        return writerService.getAllWriterResponseJson(type, search, pageSize, pageNumber);
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
        return writerService.removeWriterByPublicId(publicId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return ControllerExceptionHandler.handleValidationExceptions(exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, String> entityNotFoundExceptionHandler(ResponseStatusException exception) {
        return ControllerExceptionHandler.handleNotFoundStatusExcetions(exception);
    }
}
