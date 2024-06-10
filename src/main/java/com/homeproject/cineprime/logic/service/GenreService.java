package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Genre;
import com.homeproject.cineprime.domain.repository.GenreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    };
    public void createGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public Optional<Genre> getGenreById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("The given number is NULL, try with another number!");
        }

        Optional<Genre> genre = genreRepository.findById(id);

        if(genre.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + id);
        }

        return genre;
    }

    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    public String deleteGenre(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("The identifier is null. Give a valid identifier number!");
        }

        Optional<Genre> genre = genreRepository.findById(id);

        if(genre.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " +id);
        }

        genreRepository.delete(genre.get());

        return "Genre with identifier: " + id + " successfully deleted!";
    }

    public void updateGenre(Genre genreToUpdate) {
        if(genreToUpdate == null) {
            throw new IllegalArgumentException("The entity is null. Give a valid identifier number!");
        }

        Optional<Genre> genre = genreRepository.findById(genreToUpdate.getId());

        if(genre.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + genreToUpdate.getId());
        }

        genreRepository.save(genreToUpdate);

    }
}
