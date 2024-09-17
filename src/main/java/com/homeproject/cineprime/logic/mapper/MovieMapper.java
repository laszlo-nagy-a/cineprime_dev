package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.domain.model.*;
import com.homeproject.cineprime.logic.dto.*;

import com.homeproject.cineprime.logic.service.DirectorService;
import com.homeproject.cineprime.logic.service.GenreService;
import com.homeproject.cineprime.logic.service.StarService;
import com.homeproject.cineprime.logic.service.WriterService;
import com.homeproject.cineprime.view.response_json.MovieRequestJson;
import com.homeproject.cineprime.view.response_json.MovieResponseJson;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MovieMapper {

    @Autowired
    private final GenreService genreService;
    @Autowired
    private final WriterService writerService;
    @Autowired
    private final DirectorService directorService;
    @Autowired
    private final StarService starService;

    public static MovieDto movieToDto(Movie movie) {
        if(!(movie instanceof Movie) || movie == null) {
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
        if(!(movieDto instanceof MovieDto) || movieDto == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + movieDto.toString());
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
                    .map(writerDto -> writerDto.getPublicId())
                    .toList());
        }

        // set directorResponse to movieDto with validation
        if(movieDto.getDirectorDtoList() != null) {
            returnValue.setDirectorPublicIdJsonList(movieDto.getDirectorDtoList()
                    .stream()
                    .map(directorDto -> directorDto.getPublicId())
                    .toList());
        }

        // set starResponse to movieDto with validation
        if(movieDto.getStarDtoList() != null) {
            returnValue.setStarPublicIdJsonList(movieDto.getStarDtoList()
                    .stream()
                    .map(starDto -> starDto.getPublicId())
                    .toList());
        }

        // set genreResponse to movieDto with validation
        if(movieDto.getGenreDtoList() != null) {
            returnValue.setGenrePublicIdJsonList(movieDto.getGenreDtoList()
                    .stream()
                    .map(genreDto -> genreDto.getPublicId())
                    .toList());
        }

        return returnValue;
    }

    public MovieDto requestToDto(MovieRequestJson movieRequestJson) {
        if(!(movieRequestJson instanceof MovieRequestJson) || movieRequestJson == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + movieRequestJson.toString());
        }

        MovieDto movieDto = new MovieDto();
        movieDto.setPublicId(movieRequestJson.getPublicId());
        movieDto.setTitle(movieRequestJson.getTitle());
        movieDto.setDescription(movieRequestJson.getDescription());
        movieDto.setReleaseDate(movieRequestJson.getReleaseDate());
        movieDto.setPg(movieRequestJson.getPg());
        movieDto.setPlayTimeMin(movieRequestJson.getPlayTimeMin());

        // transform arrays to list and create the list unique elements
        Set<String> genrePublicIdList = Arrays.stream(movieRequestJson.getGenrePublicIdList()).distinct().collect(Collectors.toSet());
        Set<String> writerPublicIdList = Arrays.stream(movieRequestJson.getWriterPublicIdList()).distinct().collect(Collectors.toSet());
        Set<String> directorPublicIdList = Arrays.stream(movieRequestJson.getDirectorPublicIdList()).distinct().collect(Collectors.toSet());
        Set<String> starPublicIdList = Arrays.stream(movieRequestJson.getStarPublicIdList()).distinct().collect(Collectors.toSet());

        // creating genre dto list and set
        if(!genrePublicIdList.isEmpty()) {
            Set<GenreDto> genreDtoList = new HashSet<>();
            for(String genrePublicId : genrePublicIdList) {
                Optional<Genre> foundGenre = genreService.getGenreByPublicId(genrePublicId);
                if(foundGenre.isPresent()) {
                    genreDtoList.add(
                            GenreMapper.genreToDto(foundGenre.get()));
                }
            }
            movieDto.setGenreDtoList(genreDtoList);
        }

        // creating writer dto list
        if(!writerPublicIdList.isEmpty()) {
            Set<WriterDto> writerDtoList = new HashSet<WriterDto>();
            for(String writerPublicId : writerPublicIdList) {
                Optional<Writer> foundWriter = writerService.findByPublicId(writerPublicId);
                if(foundWriter.isPresent()) {
                    writerDtoList.add(
                            WriterMapper.writerToDto(foundWriter.get()));
                }
            }
            movieDto.setWriterDtoList(writerDtoList);
        }
        // creating director dto list
        if(!directorPublicIdList.isEmpty()) {
            Set<DirectorDto> directorDtoList = new HashSet<DirectorDto>();
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
            Set<StarDto> starDtoList = new HashSet<StarDto>();
            for(String starPublicId : starPublicIdList) {
                Optional<Star> foundStar = starService.findByPublicId(starPublicId);
                if(foundStar.isPresent()) {
                    starDtoList.add(
                            StarMapper.starToDto(foundStar.get()));
                }
            }
            movieDto.setStarDtoList(starDtoList);
        }
        return movieDto;
    }

    public Movie dtoToMovie(MovieDto movieDto) {
        if(!(movieDto instanceof MovieDto) ||movieDto == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + movieDto.toString());
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
