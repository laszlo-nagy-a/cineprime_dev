package com.homeproject.cineprime.logic.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MovieDto extends AbastractEntityDto {
    private String title;
    private String description;
    private Date releaseDate;
    private Byte pg;
    private Short playTimeMin;
    private Timestamp lastModifiedAt;
    private Set<WriterDto> writerDtoList;
    private Set<DirectorDto> directorDtoList;
    private Set<StarDto> starDtoList;
    private Set<GenreDto> genreDtoList;
}
