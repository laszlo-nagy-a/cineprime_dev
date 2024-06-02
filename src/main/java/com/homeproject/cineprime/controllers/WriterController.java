package com.homeproject.cineprime.controllers;

import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.models.Writer;
import com.homeproject.cineprime.services.WriterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Writer createWriter(@RequestBody Writer writer) {
        return writerService.createWriter(writer);
    }

    @PutMapping("/writers")
    public Writer updateWriter(@RequestBody Writer writerToUpdate) {
        return writerService.updateWriter(writerToUpdate);
    }

}
