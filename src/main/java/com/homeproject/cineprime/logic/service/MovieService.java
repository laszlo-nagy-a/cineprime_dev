package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Movie;
import com.homeproject.cineprime.domain.repository.MovieRepository;
import com.homeproject.cineprime.logic.dto.MovieDto;
import com.homeproject.cineprime.logic.mapper.MovieMapper;
import com.homeproject.cineprime.logic.util.PublicIdGenerator;
import com.homeproject.cineprime.view.response_json.MovieRequestJson;
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

    //TODO: duplikált kapcsolatok kezelése
    public MovieResponseJson createMovie(MovieRequestJson movieRequestJson) {
        if(!(movieRequestJson instanceof MovieRequestJson) || movieRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        MovieDto movieDto = new MovieDto();
        movieDto = MovieMapper.requestToDto(movieRequestJson);

        Movie newEntity = new Movie();
        newEntity = MovieMapper.dtoToMovie(movieDto);
        newEntity.setPublicId(PublicIdGenerator.generateId(30));

        Movie savedMovie = movieRepository.save(newEntity);
        MovieDto savedMovieDto = MovieMapper.movieToDto(savedMovie);
        MovieResponseJson returnValue = new MovieResponseJson();
        returnValue = MovieMapper.dtoToResponse(savedMovieDto);

        return returnValue;
    }

    public MovieResponseJson updateMovie(MovieRequestJson movieRequestJson) {
        if(!(movieRequestJson instanceof MovieRequestJson) || movieRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        Optional<Movie> movieToUpdate = movieRepository.findByPublicId(movieRequestJson.getPublicId());

        if(movieToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with ID: " + movieRequestJson.getPublicId());
        }

        Movie movie = new Movie();
        MovieDto movieDto = new MovieDto();
        movieDto = MovieMapper.requestToDto(movieRequestJson);
        movie = MovieMapper.dtoToMovie(movieDto);

        movie.setId(movieToUpdate.get().getId());
        Movie updatedMovie = movieRepository.save(movie);

        MovieDto savedMovieDto = new MovieDto();
        savedMovieDto = MovieMapper.movieToDto(updatedMovie);
        MovieResponseJson returnValue = MovieMapper.dtoToResponse(savedMovieDto);

        return returnValue;
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
