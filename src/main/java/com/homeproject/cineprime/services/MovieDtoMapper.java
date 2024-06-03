package com.homeproject.cineprime.services;

import com.homeproject.cineprime.dtos.MovieCardViewDto;
import com.homeproject.cineprime.dtos.MovieDetailViewDto;
import com.homeproject.cineprime.models.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieDtoMapper {

    public MovieDetailViewDto movieToMovieDetailViewDto(Movie movie) {

        if(movie == null) {
            throw new IllegalArgumentException("The given argument is NULL! Try with Movie object");
        }

        MovieDetailViewDto movieDetailViewDto = new MovieDetailViewDto();
        List<String> namesOfDirectors = new ArrayList<>();
        List<String> namesOfWriters = new ArrayList<>();
        List<String> namesOfGenres = new ArrayList<>();
        List<String> namesOfStars = new ArrayList<>();

        movieDetailViewDto.setMovieTitle(movie.getTitle());
        movieDetailViewDto.setMovieDescription(movie.getDescription());
        movieDetailViewDto.setReleaseDate(movie.getReleaseDate());
        movieDetailViewDto.setPlayTimeMin(movie.getPlayTimeMin());
        movieDetailViewDto.setPg(movie.getPg());
        // TODO: másképp megírni az osztályokat, hogy egy generikus metódussal meg lehessen írni a listázó függvényt? (T extends PersonData)?
        movie.getDirectorList()
                .forEach(x -> {
                    namesOfDirectors.add(x.getPersonData().getFirstName() + " " + x.getPersonData().getLastName());
                });
        movie.getStarList()
                .forEach(x -> {
                    namesOfStars.add(x.getPersonData().getFirstName() + " " + x.getPersonData().getLastName());
                });
        movie.getWriterList()
                .forEach(x -> {
                    namesOfWriters.add(x.getPersonData().getFirstName() + " " + x.getPersonData().getLastName());
                });
        movie.getGenreList()
                .forEach(x -> {
                    namesOfGenres.add(x.getName());
                });
        movieDetailViewDto.setDirectorNames(namesOfDirectors);
        movieDetailViewDto.setWriterNames(namesOfWriters);
        movieDetailViewDto.setGenreList(namesOfGenres);
        movieDetailViewDto.setStarList(namesOfStars);

        return movieDetailViewDto;
    }

    public static MovieCardViewDto movieToMovieCardDto(Movie movie) {

        if(movie == null) {
            throw new IllegalArgumentException("The given argument is NULL! Try with Movie object");
        }

        MovieCardViewDto movieCardViewDto = new MovieCardViewDto();
        List<String> genreNameList = new ArrayList<>();

        movieCardViewDto.setMovieTitle(movie.getTitle());;
        movieCardViewDto.setPg(movie.getPg());

        movie.getGenreList().forEach(x -> {
            genreNameList.add(x.getName());
        });

        movieCardViewDto.setGenreList(genreNameList);

        return movieCardViewDto;
    }
}
