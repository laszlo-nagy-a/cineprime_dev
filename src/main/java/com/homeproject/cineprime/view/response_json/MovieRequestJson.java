package com.homeproject.cineprime.view.response_json;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieRequestJson {
    private String publicId;
    private String title;
    private String description;
    private Date releaseDate;
    private Byte pg;
    private Short playTimeMin;
    private String[] writerPublicIdList;
    private String[] directorPublicIdList;
    private String[] starPublicIdList;
    private String[] genrePublicIdList;
}
