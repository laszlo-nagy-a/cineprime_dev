package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Movie;
import com.homeproject.cineprime.domain.repository.MovieRepository;
import com.homeproject.cineprime.logic.dto.MovieDto;
import com.homeproject.cineprime.logic.mapper.MovieMapper;
import com.homeproject.cineprime.view.response_json.MovieResponseJson;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public List<MovieResponseJson> getAllMovieResponseJson() {
        List<Movie> allMovies = movieRepository.findAll();
        List<MovieDto> allMovieDto = allMovies
                .stream()
                .map(MovieMapper::movieToDto)
                .toList();

        return allMovieDto
                .stream()
                .map(MovieMapper::dtoToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MovieResponseJson getMovieByPublicId(String publicId) {
        if(StringUtils.isEmpty(publicId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    """
                    The given ID is NULL or empty string, 
                    try with another ID! Given ID: 
                    """ + publicId);
        }

        Optional<Movie> movie = movieRepository.findByPublicId(publicId);

        if(movie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with ID: " + publicId);
        }

        MovieDto movieDto = new MovieDto();
        movieDto = MovieMapper.movieToDto(movie.get());
        MovieResponseJson returnValue = new MovieResponseJson();
        returnValue = MovieMapper.dtoToResponse(movieDto);

        return returnValue;
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
