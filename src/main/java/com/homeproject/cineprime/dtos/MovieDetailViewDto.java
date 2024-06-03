package com.homeproject.cineprime.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
// TODO: recordot lehetne csinálni ebből az osztályból és akkor nem kellene annotálni se?
public class MovieDetailViewDto {

    private String movieTitle;
    private String movieDescription;
    private Date releaseDate;
    private short playTimeMin;
    private Byte pg;
    private List<String> writerNames;
    private List<String> directorNames;
    private List<String> genreList;
    private List<String> starList;

}
