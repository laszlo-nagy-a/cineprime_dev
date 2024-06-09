package com.homeproject.cineprime.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public record MovieDetailViewDto (
        String movieTitle,
        String movieDescription,
        Date releaseDate,
        short playTimeMin,
        Byte pg,
        List<String> writerNames,
        List<String> directorNames,
        List<String> genreList,
        List<String> starList
        ) {
}
