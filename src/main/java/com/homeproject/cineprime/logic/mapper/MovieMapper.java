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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Component
public class MovieMapper {

    private static GenreService genreService;
    private static WriterService writerService;
    private static DirectorService directorService;
    private static StarService starService;

    @Autowired
    private GenreService genreServiceAutowire;
    @Autowired
    private WriterService writerServiceAutowire;
    @Autowired
    private DirectorService directorServiceAutowire;
    @Autowired
    private StarService starServiceAutowire;

    @PostConstruct
    private void initServices() {
        genreService = genreServiceAutowire;
        writerService = writerServiceAutowire;
        directorService = directorServiceAutowire;
        starService = starServiceAutowire;
    }



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

        if(movie.getWriterList() != null) {
            returnValue.setWriterDtoList(movie.getWriterList()
                    .stream()
                    .map(WriterMapper::writerToDto)
                    .toList());
        }

        if(movie.getDirectorList() != null) {
            returnValue.setDirectorDtoList(movie.getDirectorList()
                    .stream()
                    .map(DirectorMapper::directorToDto)
                    .toList());
        }

        if(movie.getStarList() != null) {
            returnValue.setStarDtoList(movie.getStarList()
                    .stream()
                    .map(StarMapper::starToDto)
                    .toList());
        }

        if(movie.getGenreList() != null) {
            returnValue.setGenreDtoList(movie.getGenreList()
                    .stream()
                    .map(GenreMapper::genreToDto)
                    .toList());
        }

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

        // set writerResponse to movieDto with validation
        if(movieDto.getWriterDtoList() != null) {
            returnValue.setWriterResponseJsonList(movieDto.getWriterDtoList()
                    .stream()
                    .map(WriterMapper::dtoToResponse)
                    .toList());
        }

        // set directorResponse to movieDto with validation
        if(movieDto.getDirectorDtoList() != null) {
            returnValue.setDirectorResponseJsonList(movieDto.getDirectorDtoList()
                    .stream()
                    .map(DirectorMapper::dtoToResponse)
                    .toList());
        }

        // set starResponse to movieDto with validation
        if(movieDto.getStarDtoList() != null) {
            returnValue.setStarResponseJsonList(movieDto.getStarDtoList()
                    .stream()
                    .map(StarMapper::dtoToResponse)
                    .toList());
        }

        // set genreResponse to movieDto with validation
        if(movieDto.getGenreDtoList() != null) {
            returnValue.setGenreResponseJsonList(movieDto.getGenreDtoList()
                    .stream()
                    .map(GenreMapper::dtoToResponse)
                    .toList());
        }

        return returnValue;
    }

    public static MovieDto requestToDto(MovieRequestJson movieRequestJson) {
        if(!(movieRequestJson instanceof MovieRequestJson) ||movieRequestJson == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + movieRequestJson.toString());
        }

        MovieDto movieDto = new MovieDto();
        movieDto.setPublicId(movieRequestJson.getPublicId());
        movieDto.setTitle(movieRequestJson.getTitle());
        movieDto.setDescription(movieRequestJson.getDescription());
        movieDto.setReleaseDate(movieRequestJson.getReleaseDate());
        movieDto.setPg(movieRequestJson.getPg());
        movieDto.setPlayTimeMin(movieRequestJson.getPlayTimeMin());

        //TODO: refaktor Set-re?

        // transform arrays to list and create the list unique elements
        List<String> genrePublicIdList = Arrays.stream(movieRequestJson.getGenrePublicIdList()).distinct().toList();
        List<String> writerPublicIdList = Arrays.stream(movieRequestJson.getWriterPublicIdList()).distinct().toList();
        List<String> directorPublicIdList = Arrays.stream(movieRequestJson.getDirectorPublicIdList()).distinct().toList();
        List<String> starPublicIdList = Arrays.stream(movieRequestJson.getStarPublicIdList()).distinct().toList();

        // creating genre dto list and set
        if(!genrePublicIdList.isEmpty()) {
            List<GenreDto> genreDtoList = new ArrayList<GenreDto>();
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
            List<WriterDto> writerDtoList = new ArrayList<WriterDto>();
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
            List<DirectorDto> directorDtoList = new ArrayList<DirectorDto>();
            for(String directorPublicId : directorPublicIdList) {
                Optional<Director> foundDirector = directorService.findByPublicId(directorPublicId);
                if(foundDirector.isPresent()) {
                    directorDtoList.add(
                            DirectorMapper.directorToDto(foundDirector.get()));
                }
            }
            movieDto.setDirectorDtoList(directorDtoList);
        }
        // star director dto list
        if(!starPublicIdList.isEmpty()) {
            List<StarDto> starDtoList = new ArrayList<StarDto>();
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

    public static Movie dtoToMovie(MovieDto movieDto) {
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
        List<WriterDto> writerDtoList = movieDto.getWriterDtoList();
        if(writerDtoList != null) {
        returnValue.setWriterList(movieDto.getWriterDtoList()
                .stream()
                .map(WriterMapper::dtoToWriter)
                .toList());
        }

        // create director to dto with validation
        List<DirectorDto> directorDtoList = movieDto.getDirectorDtoList();
        if(directorDtoList != null) {
            returnValue.setDirectorList(movieDto.getDirectorDtoList()
                    .stream()
                    .map(DirectorMapper::dtoToDirector)
                    .toList());
        }

        // create star to dto with validation
        List<StarDto> starDtoList = movieDto.getStarDtoList();
        if(starDtoList != null) {
            returnValue.setStarList(movieDto.getStarDtoList()
                    .stream()
                    .map(StarMapper::dtoToStar)
                    .toList());
        }

        // create genre to dto with validation
        List<GenreDto> genreDtoList = movieDto.getGenreDtoList();
        if(genreDtoList != null) {
            returnValue.setGenreList(movieDto.getGenreDtoList()
                    .stream()
                    .map(GenreMapper::dtoToGenre)
                    .toList());
        }

        return returnValue;
    }
}
