package com.homeproject.cineprime.logic.dto;

import java.util.List;

public record MovieCardViewDto(String movieTitle, Byte pg,List<String> genreList) {

}
