package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.domain.model.Movie;
import com.homeproject.cineprime.logic.dto.MovieDto;
import com.homeproject.cineprime.view.response_json.MovieResponseJson;

public class MovieMapper {

    public static MovieDto movieToDto(Movie movie) {
        if(!(movie instanceof Movie) ||movie == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + movie.toString());
        }

        MovieDto returnValue = new MovieDto();
        returnValue.setId(movie.getId());
        returnValue.setPublicId(movie.getPublicId());
        returnValue.setTitle(movie.getTitle());
        returnValue.setDescription(movie.getDescription());
        returnValue.setReleaseDate(movie.getReleaseDate());
        returnValue.setPg(movie.getPg());
        returnValue.setPlayTimeMin(movie.getPlayTimeMin());
        returnValue.setLastModifiedAt(movie.getLastModifiedAt());
        returnValue.setWriterList(movie.getWriterList()
                    .stream()
                    .map(WriterMapper::writerToDto)
                    .toList());
        returnValue.setDirectorList(movie.getDirectorList()
                .stream()
                .map(DirectorMapper::directorToDto)
                .toList());
        returnValue.setStarList(movie.getStarList()
                .stream()
                .map(StarMapper::starToDto)
                .toList());
        returnValue.setGenreList(movie.getGenreList()
                .stream()
                .map(GenreMapper::genreToDto)
                .toList());

        return returnValue;
    }

    public static MovieResponseJson dtoToResponse(MovieDto movieDto) {
        if(!(movieDto instanceof MovieDto) ||movieDto == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + movieDto.toString());
        }

        MovieResponseJson returnValue = new MovieResponseJson();
        returnValue.setPublicId(movieDto.getPublicId());
        returnValue.setTitle(movieDto.getTitle());
        returnValue.setDescription(movieDto.getDescription());
        returnValue.setReleaseDate(movieDto.getReleaseDate());
        returnValue.setPg(movieDto.getPg());
        returnValue.setPlayTimeMin(movieDto.getPlayTimeMin());

        returnValue.setWriterResponseJsonList(movieDto.getWriterList()
                .stream()
                .map(WriterMapper::dtoToResponse)
                .toList());
        returnValue.setDirectorResponseJsonList(movieDto.getDirectorList()
                .stream()
                .map(DirectorMapper::dtoToResponse)
                .toList());
        returnValue.setStarResponseJsonList(movieDto.getStarList()
                .stream()
                .map(StarMapper::dtoToResponse)
                .toList());
        returnValue.setGenreResponseJsonList(movieDto.getGenreList()
                .stream()
                .map(GenreMapper::dtoToResponse)
                .toList());

        return returnValue;
    }
}
