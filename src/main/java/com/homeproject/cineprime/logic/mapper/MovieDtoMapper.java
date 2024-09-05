package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.logic.dto.MovieCardViewDto;
import com.homeproject.cineprime.logic.dto.MovieDetailViewDto;
import com.homeproject.cineprime.domain.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieDtoMapper {

    public MovieDetailViewDto movieToMovieDetailViewDto(Movie movie) {

        if(movie == null) {
            throw new IllegalArgumentException("The given argument is NULL! Try with Movie object");
        }

        // TODO: ezeknél a neveknél át kellene adni ID-t is?
        // (így a movie detail oldalról lehetne navigálni pl.: az író detail oldalára)
        List<String> writerNames = new ArrayList<>();
        movie.getWriterList().forEach(
                x -> writerNames.add(PersonDataMapper.personDataToName(x.getPersonData()))
        );

        List<String> directorNames = new ArrayList<>();
        movie.getDirectorList().forEach(
                x -> directorNames.add(PersonDataMapper.personDataToName(x.getPersonData()))
        );

        List<String> starNames = new ArrayList<>();
        movie.getStarList().forEach(
                x -> starNames.add(PersonDataMapper.personDataToName(x.getPersonData()))
        );

        Set<String> genreNames = new HashSet<>();
        movie.getGenreList().forEach(
                x -> genreNames.add(x.getName())
        );


        return new MovieDetailViewDto(
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getPlayTimeMin(),
                movie.getPg(),
                writerNames,
                directorNames,
                genreNames,
                starNames
        );
    }

    public static MovieCardViewDto movieToMovieCardDto(Movie movie) {

        if (movie == null) {
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
