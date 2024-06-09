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


        List<String> namesOfDirectors = new ArrayList<>();
        List<String> namesOfWriters = new ArrayList<>();
        List<String> namesOfGenres = new ArrayList<>();
        List<String> namesOfStars = new ArrayList<>();

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

        MovieDetailViewDto movieDetailViewDto = new MovieDetailViewDto(
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getPlayTimeMin(),
                movie.getPg(),
                namesOfWriters,
                namesOfDirectors,
                namesOfGenres,
                namesOfStars
        );
        return movieDetailViewDto;
    }

    public static MovieCardViewDto movieToMovieCardDto(Movie movie) {

        if(movie == null) {
            throw new IllegalArgumentException("The given argument is NULL! Try with Movie object");
        }

        List<String> genreNameList = new ArrayList<>();

        movie.getGenreList().forEach(x -> {
            genreNameList.add(x.getName());
        });

        return new MovieCardViewDto(
                movie.getTitle(),
                movie.getPg(),
                genreNameList
        );
    }
}
