package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Director;
import com.homeproject.cineprime.domain.repository.DirectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    private DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    @Transactional(readOnly = true)
    public Director getDirectorById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("The given number is NULL, try with another number!");
        }

        Optional<Director> director = directorRepository.findById(id);
        return director.get();
    }

    public void createDirector(Director director) {
        directorRepository.save(director);
    }

    public List<Director> getAllDirector() {
        return directorRepository.findAll();
    }

    public String deleteDirector(Long id) {

        if(id == null) {
            throw new IllegalArgumentException("The identifier is null. Give a valid identifier number!");
        }

        Optional<Director> director = directorRepository.findById(id);

        if(director.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " +id);
        }

        directorRepository.delete(director.get());
        return "Director with identifier: " + id + " successfully deleted!";
    }

    public void updateDirector(Director directorToUpdate) {
        if(directorToUpdate == null) {
            throw new IllegalArgumentException("The entity is null. Give a valid identifier number!");
        }

        Optional<Director> director = directorRepository.findById(directorToUpdate.getId());

        if(director.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + directorToUpdate.getId());
        }

        directorRepository.save(directorToUpdate);
    }

}
