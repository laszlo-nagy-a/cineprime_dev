package com.homeproject.cineprime.view.response_json;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieResponseJson {
    private String publicId;
    private String title;
    private String description;
    private Date releaseDate;
    private Byte pg;
    private Short playTimeMin;
    private List<WriterResponseJson> writerResponseJsonList;
    private List<DirectorResponseJson> directorResponseJsonList;
    private List<StarResponseJson> starResponseJsonList;
    private List<GenreResponseJson> genreResponseJsonList;
}
