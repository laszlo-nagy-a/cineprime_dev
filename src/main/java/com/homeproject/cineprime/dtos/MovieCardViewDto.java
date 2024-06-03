package com.homeproject.cineprime.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieCardViewDto {

    private String movieTitle;
    private Byte pg;
    private List<String> genreList;
}
