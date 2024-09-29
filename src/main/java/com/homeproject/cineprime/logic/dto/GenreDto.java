package com.homeproject.cineprime.logic.dto;

import com.homeproject.cineprime.domain.model.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenreDto extends AbastractEntityDto{
    private String name;
    private List<Movie> movieList;
}
