package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.logic.dto.MovieCardViewDto;
import com.homeproject.cineprime.logic.dto.MovieDetailViewDto;
import com.homeproject.cineprime.domain.model.Movie;
import com.homeproject.cineprime.domain.repository.MovieRepository;
import com.homeproject.cineprime.logic.mapper.MovieDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieDtoMapper movieDtoMapper;

    public MovieService (MovieRepository movieRepository, MovieDtoMapper movieDtoMapper) {
        this.movieRepository = movieRepository;
        this.movieDtoMapper = movieDtoMapper;
    }

    @Transactional(readOnly = true)
    public MovieDetailViewDto getMovieById(Long id) {

        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty()) {
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

    public void createMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void updateMovie(Movie movieToUpdate) {
        if(movieToUpdate == null) {
            throw new IllegalArgumentException("The entity is null. Give a valid identifier number!");
        }

        Optional<Movie> movie = movieRepository.findById(movieToUpdate.getId());

        if(movie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + movieToUpdate.getId());
        }

        movieRepository.save(movieToUpdate);
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
