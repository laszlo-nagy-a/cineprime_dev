package com.homeproject.cineprime.logic.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record MovieDetailViewDto (
        String movieTitle,
        String movieDescription,
        Date releaseDate,
        short playTimeMin,
        Byte pg,
        List<String> writerNames,
        List<String> directorNames,
        Set<String> genreList,
        List<String> starList
        ) {
}
