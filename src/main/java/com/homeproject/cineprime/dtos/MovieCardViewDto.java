package com.homeproject.cineprime.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record MovieCardViewDto(String movieTitle, Byte pg,List<String> genreList) {

}
