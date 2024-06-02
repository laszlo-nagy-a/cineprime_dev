package com.homeproject.cineprime.services;

import com.homeproject.cineprime.models.Director;
import com.homeproject.cineprime.models.Movie;
import com.homeproject.cineprime.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Optional<Movie> getMovieById(Long id) {

        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty()) {
            // TODO: Jó helyen van a hiba dobva (nem controllerben kell)?
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with ID: " + id);
        }

        return movie;
    }
}
