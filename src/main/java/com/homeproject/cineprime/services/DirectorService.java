package com.homeproject.cineprime.services;

import com.homeproject.cineprime.models.Director;
import com.homeproject.cineprime.repositories.DirectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    private DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Optional<Director> getDirectorById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("The given number is NULL, try with another number!");
        }

        Optional<Director> director = directorRepository.findById(id);
        if(director.isEmpty()) {
            // TODO: Jó helyen van a hiba dobva (nem controllerben kell)?
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + id);
        }

        return director;
    }

    public Director createDirector(Director director) {
        return directorRepository.save(director);
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

    public Director updateDirector(Director directorToUpdate) {
        if(directorToUpdate == null) {
            throw new IllegalArgumentException("The entity is null. Give a valid identifier number!");
        }

        Optional<Director> director = directorRepository.findById(directorToUpdate.getId());

        if(director.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + directorToUpdate.getId());
        }

        return directorRepository.save(directorToUpdate);
    }

}
