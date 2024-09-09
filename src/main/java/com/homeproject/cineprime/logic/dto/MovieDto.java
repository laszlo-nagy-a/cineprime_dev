package com.homeproject.cineprime.logic.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieDto {
    private Long id;
    private String publicId;
    private String title;
    private String description;
    private Date releaseDate;
    private Byte pg;
    private Short playTimeMin;
    private Timestamp lastModifiedAt;
    private List<WriterDto> writerDtoList;
    private List<DirectorDto> directorDtoList;
    private List<StarDto> starDtoList;
    private List<GenreDto> genreDtoList;
}
