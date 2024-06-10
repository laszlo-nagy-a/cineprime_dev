package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Star;
import com.homeproject.cineprime.domain.repository.StarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StarService {

    private StarRepository starRepository;
    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public Optional<Star> getStarById(Long id) {

        Optional<Star> star = starRepository.findById(id);

        if(star.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Star not found with ID: " + id);
        }

        return star;
    }

    public void createStar(Star star) {
         starRepository.save(star);
    }

    public List<Star> getAllStar() {
        return starRepository.findAll();
    }

    public String deleteStar(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("The identifier is null. Give a valid identifier number!");
        }

        Optional<Star> star = starRepository.findById(id);

        if(star.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Star not found with ID: " +id);
        }

        starRepository.delete(star.get());
        return "Star with identifier: " + id + " successfully deleted!";
    }

    public void updateGenre(Star starToUpdate) {
        if(starToUpdate == null) {
            throw new IllegalArgumentException("The entity is null. Give a valid identifier number!");
        }

        Optional<Star> star = starRepository.findById(starToUpdate.getId());

        if(star.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Star not found with ID: " + starToUpdate.getId());
        }

        starRepository.save(starToUpdate);
    }
}
