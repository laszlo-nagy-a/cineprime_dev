package com.homeproject.cineprime.services;

import com.homeproject.cineprime.models.Director;
import com.homeproject.cineprime.models.Writer;
import com.homeproject.cineprime.repositories.WriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WriterService {

    private WriterRepository writerRepository;

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }


    public List<Writer> getAllWriter() {
        return writerRepository.findAll();
    }

    public Optional<Writer> getWriterById(Long id) {

        Optional<Writer> writer = writerRepository.findById(id);
        if(writer.isEmpty()) {
            // TODO: Jó helyen van a hiba dobva (nem controllerben kell)?
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + id);
        }
        return writer;
    }


    public Writer createWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    public Writer updateWriter(Writer writerToUpdate) {

        Optional<Writer> writer = writerRepository.findById(writerToUpdate.getId());

        if(writer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Writer not found with ID: " + writerToUpdate.getId());
        }

        return writerRepository.save(writerToUpdate);
    }
}
