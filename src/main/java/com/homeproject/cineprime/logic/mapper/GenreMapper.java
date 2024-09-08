package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.domain.model.Genre;
import com.homeproject.cineprime.logic.dto.GenreDto;
import com.homeproject.cineprime.view.request_json.GenreRequestJson;
import com.homeproject.cineprime.view.response_json.GenreResponseJson;
import org.springframework.stereotype.Service;

@Service
public class GenreMapper {

    public static GenreDto requestToDto(GenreRequestJson requestEntity) {

        if(!(requestEntity instanceof GenreRequestJson) ||requestEntity == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + requestEntity.toString());
        }

        GenreDto returnValue = new GenreDto();

        returnValue.setPublicId(requestEntity.getPublicId());
        returnValue.setName(requestEntity.getName());

        return returnValue;

    }

    public static GenreResponseJson dtoToResponse(GenreDto genreDto) {

        if(!(genreDto instanceof GenreDto) ||genreDto == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + genreDto.toString());
        }

        GenreResponseJson returnValue = new GenreResponseJson();
        returnValue.setPublicId(genreDto.getPublicId());
        returnValue.setName(genreDto.getName());

        return returnValue;

    }

    public static Genre dtoToGenre(GenreDto genreDto) {

        if(!(genreDto instanceof GenreDto) ||genreDto == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + genreDto.toString());
        }

        Genre returnValue = new Genre();
        returnValue.setId(genreDto.getId());
        returnValue.setPublicId(genreDto.getPublicId());
        returnValue.setName(genreDto.getName());
        returnValue.setMovieList(genreDto.getMovieList());

        return returnValue;

    }

    public static GenreDto genreToDto(Genre genre) {

        if(!(genre instanceof Genre) ||genre == null) {
            throw new IllegalArgumentException("Given args are not comaptible. Arg Object values: " + genre.toString());
        }

        GenreDto returnValue = new GenreDto();
        returnValue.setId(genre.getId());
        returnValue.setPublicId(genre.getPublicId());
        returnValue.setName(genre.getName());
        returnValue.setMovieList(genre.getMovieList());

        return returnValue;

    }

}
