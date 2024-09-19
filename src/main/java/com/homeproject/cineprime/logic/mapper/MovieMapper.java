package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.domain.model.*;
import com.homeproject.cineprime.logic.dto.*;

import com.homeproject.cineprime.logic.service.DirectorService;
import com.homeproject.cineprime.logic.service.GenreService;
import com.homeproject.cineprime.logic.service.StarService;
import com.homeproject.cineprime.logic.service.WriterService;
import com.homeproject.cineprime.view.response_json.MovieRequestJson;
import com.homeproject.cineprime.view.response_json.MovieResponseJson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MovieMapper {
    private final GenreService genreService;
    private final WriterService writerService;
    private final DirectorService directorService;
    private final StarService starService;

    public static MovieDto movieToDto(Movie movie) {
        if(movie == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
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

        if(movie.getWriterList() != null) {
            returnValue.setWriterDtoList(movie.getWriterList()
                    .stream()
                    .map(WriterMapper::writerToDto)
                    .collect(Collectors.toSet()));
        }

        if(movie.getDirectorList() != null) {
            returnValue.setDirectorDtoList(movie.getDirectorList()
                    .stream()
                    .map(DirectorMapper::directorToDto)
                    .collect(Collectors.toSet()));
        }

        if(movie.getStarList() != null) {
            returnValue.setStarDtoList(movie.getStarList()
                    .stream()
                    .map(StarMapper::starToDto)
                    .collect(Collectors.toSet()));
        }

        if(movie.getGenreList() != null) {
            returnValue.setGenreDtoList(movie.getGenreList()
                    .stream()
                    .map(GenreMapper::genreToDto)
                    .collect(Collectors.toSet()));
        }

        return returnValue;
    }

    public static MovieResponseJson dtoToResponse(MovieDto movieDto) {
        if(movieDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        MovieResponseJson returnValue = new MovieResponseJson();
        returnValue.setPublicId(movieDto.getPublicId());
        returnValue.setTitle(movieDto.getTitle());
        returnValue.setDescription(movieDto.getDescription());
        returnValue.setReleaseDate(movieDto.getReleaseDate());
        returnValue.setPg(movieDto.getPg());
        returnValue.setPlayTimeMin(movieDto.getPlayTimeMin());

        // set writerResponse to movieDto with validation
        if(movieDto.getWriterDtoList() != null) {
            returnValue.setWriterPublicIdJsonList(movieDto.getWriterDtoList()
                    .stream()
                    .map(AbastractEntityDto::getPublicId)
                    .toList());
        }

        // set directorResponse to movieDto with validation
        if(movieDto.getDirectorDtoList() != null) {
            returnValue.setDirectorPublicIdJsonList(movieDto.getDirectorDtoList()
                    .stream()
                    .map(AbastractEntityDto::getPublicId)
                    .toList());
        }

        // set starResponse to movieDto with validation
        if(movieDto.getStarDtoList() != null) {
            returnValue.setStarPublicIdJsonList(movieDto.getStarDtoList()
                    .stream()
                    .map(AbastractEntityDto::getPublicId)
                    .toList());
        }

        // set genreResponse to movieDto with validation
        if(movieDto.getGenreDtoList() != null) {
            returnValue.setGenrePublicIdJsonList(movieDto.getGenreDtoList()
                    .stream()
                    .map(AbastractEntityDto::getPublicId)
                    .toList());
        }

        return returnValue;
    }

    public MovieDto requestToDto(MovieRequestJson movieRequestJson) {
        if(movieRequestJson == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        MovieDto movieDto = new MovieDto();
        movieDto.setPublicId(movieRequestJson.getPublicId());
        movieDto.setTitle(movieRequestJson.getTitle());
        movieDto.setDescription(movieRequestJson.getDescription());
        movieDto.setReleaseDate(movieRequestJson.getReleaseDate());
        movieDto.setPg(movieRequestJson.getPg());
        movieDto.setPlayTimeMin(movieRequestJson.getPlayTimeMin());

        // transform arrays to list and create the list unique elements
        Set<String> genrePublicIdList = Arrays.stream(movieRequestJson.getGenrePublicIdList()).collect(Collectors.toSet());
        Set<String> writerPublicIdList = Arrays.stream(movieRequestJson.getWriterPublicIdList()).collect(Collectors.toSet());
        Set<String> directorPublicIdList = Arrays.stream(movieRequestJson.getDirectorPublicIdList()).collect(Collectors.toSet());
        Set<String> starPublicIdList = Arrays.stream(movieRequestJson.getStarPublicIdList()).collect(Collectors.toSet());

        // creating genre dto list and set
        if(!genrePublicIdList.isEmpty()) {
            Set<GenreDto> genreDtoList = new HashSet<>();
            for(String genrePublicId : genrePublicIdList) {
                Optional<Genre> foundGenre = genreService.getGenreByPublicId(genrePublicId);
                foundGenre.ifPresent(genre -> genreDtoList.add(
                        GenreMapper.genreToDto(genre)));
            }
            movieDto.setGenreDtoList(genreDtoList);
        }

        // creating writer dto list
        if(!writerPublicIdList.isEmpty()) {
            Set<WriterDto> writerDtoList = new HashSet<>();
            for(String writerPublicId : writerPublicIdList) {
                Optional<Writer> foundWriter = writerService.findByPublicId(writerPublicId);
                foundWriter.ifPresent(writer -> writerDtoList.add(
                        WriterMapper.writerToDto(writer)));
            }
            movieDto.setWriterDtoList(writerDtoList);
        }
        // creating director dto list
        if(!directorPublicIdList.isEmpty()) {
            Set<DirectorDto> directorDtoList = new HashSet<>();
            for(String directorPublicId : directorPublicIdList) {
                Optional<Director> foundDirector = directorService.findByPublicId(directorPublicId);
                if(foundDirector.isPresent() && foundDirector.get().getDeletedAt() == null) {
                    directorDtoList.add(
                            DirectorMapper.directorToDto(foundDirector.get()));
                }
            }
            movieDto.setDirectorDtoList(directorDtoList);
        }
        // star director dto list
        if(!starPublicIdList.isEmpty()) {
            Set<StarDto> starDtoList = new HashSet<>();
            for(String starPublicId : starPublicIdList) {
                Optional<Star> foundStar = starService.findByPublicId(starPublicId);
                foundStar.ifPresent(star -> starDtoList.add(
                        StarMapper.starToDto(star)));
            }
            movieDto.setStarDtoList(starDtoList);
        }
        return movieDto;
    }

    public Movie dtoToMovie(MovieDto movieDto) {
        if(movieDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        Movie returnValue = new Movie();
        returnValue.setPublicId(movieDto.getPublicId());
        returnValue.setTitle(movieDto.getTitle());
        returnValue.setDescription(movieDto.getDescription());
        returnValue.setReleaseDate(movieDto.getReleaseDate());
        returnValue.setPg(movieDto.getPg());
        returnValue.setPlayTimeMin(movieDto.getPlayTimeMin());
        returnValue.setLastModifiedAt(movieDto.getLastModifiedAt());

        // create writer to dto with validation
        Set<WriterDto> writerDtoList = movieDto.getWriterDtoList();
        if(writerDtoList != null) {
        returnValue.setWriterList(movieDto.getWriterDtoList()
                .stream()
                .map(WriterMapper::dtoToWriter)
                .toList());
        }

        // create director to dto with validation
        Set<DirectorDto> directorDtoList = movieDto.getDirectorDtoList();
        if(directorDtoList != null) {
            returnValue.setDirectorList(movieDto.getDirectorDtoList()
                    .stream()
                    .map(DirectorMapper::dtoToDirector)
                    .toList());
        }

        // create star to dto with validation
        Set<StarDto> starDtoList = movieDto.getStarDtoList();
        if(starDtoList != null) {
            returnValue.setStarList(movieDto.getStarDtoList()
                    .stream()
                    .map(StarMapper::dtoToStar)
                    .toList());
        }

        // create genre to dto with validation
        Set<GenreDto> genreDtoList = movieDto.getGenreDtoList();
        if(genreDtoList != null) {
            returnValue.setGenreList(movieDto.getGenreDtoList()
                    .stream()
                    .map(GenreMapper::dtoToGenre)
                    .toList());
        }

        return returnValue;
    }
}
