package com.homeproject.cineprime.services;

import com.homeproject.cineprime.dtos.MovieCardViewDto;
import com.homeproject.cineprime.dtos.MovieDetailViewDto;
import com.homeproject.cineprime.models.Genre;
import com.homeproject.cineprime.models.Movie;
import com.homeproject.cineprime.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private MovieDtoMapper movieDtoMapper;

    public MovieService (MovieRepository movieRepository, MovieDtoMapper movieDtoMapper) {
        this.movieRepository = movieRepository;
        this.movieDtoMapper = movieDtoMapper;
    }

    public MovieDetailViewDto getMovieById(Long id) {

        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty()) {
            // TODO: Jó helyen van a hiba dobva (nem controllerben kell)?
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with ID: " + id);
        }

        return movieDtoMapper.movieToMovieDetailViewDto(movie.get());
    }

    public List<MovieCardViewDto> getAllMovie() {
        List<Movie> allMovie = movieRepository.findAll();

        return allMovie.stream()
                .map(MovieDtoMapper::movieToMovieCardDto)
                .toList();
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movieToUpdate) {
        if(movieToUpdate == null) {
            throw new IllegalArgumentException("The entity is null. Give a valid identifier number!");
        }

        Optional<Movie> movie = movieRepository.findById(movieToUpdate.getId());

        if(movie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + movieToUpdate.getId());
        }

        return movieRepository.save(movieToUpdate);
    }

    public String deleteMovie(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("The identifier is null. Give a valid identifier number!");
        }

        Optional<Movie> movie = movieRepository.findById(id);

        if(movie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with ID: " +id);
        }

        movieRepository.delete(movie.get());

        return "Movie with identifier: " + id + " successfully deleted!";
    }
}
