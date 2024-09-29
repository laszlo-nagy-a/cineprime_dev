package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Genre;
import com.homeproject.cineprime.domain.repository.GenreRepository;
import com.homeproject.cineprime.logic.dto.GenreDto;
import com.homeproject.cineprime.logic.service.search.GenreSearchService;
import com.homeproject.cineprime.logic.util.PublicIdGenerator;
import com.homeproject.cineprime.view.request_json.GenreRequestJson;
import com.homeproject.cineprime.view.response_json.GenreResponseJson;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.homeproject.cineprime.logic.mapper.GenreMapper;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreSearchService genreSearchService;
    public GenreService(GenreRepository genreRepository, GenreSearchService genreSearchService) {
        this.genreRepository = genreRepository;
        this.genreSearchService = genreSearchService;
    }

    @Transactional(readOnly = true)
    public List<GenreResponseJson> getAllGenreResponseJson(String type, String search, Optional<Integer> pageSizeOptional, Optional<Integer> pageNumberOptional) throws ResponseStatusException {
        List<Genre> resultSet = new ArrayList<>();
        resultSet = genreSearchService.searhByTypeAndSearch(type, search, pageSizeOptional, pageNumberOptional);

        if(resultSet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genres not found with these conditions, try with another!" );
        }

        List<GenreDto> resultGenreDto = resultSet
                .stream()
                .map(GenreMapper::genreToDto)
                .toList();

        return resultGenreDto
                .stream()
                .map(GenreMapper::dtoToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public GenreResponseJson getGenreResponseJsonById(String publicId) throws ResponseStatusException {
        if(StringUtils.isEmpty(publicId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    """
                    The given ID is NULL or empty string,
                    try with another ID! Given ID:
                    """ + publicId);
        }

        Optional<Genre> genre = genreRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(genre.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + publicId);
        }

        GenreDto genreDto = new GenreDto();
        genreDto = GenreMapper.genreToDto(genre.get());
        GenreResponseJson returnValue = new GenreResponseJson();
        returnValue = GenreMapper.dtoToResponse(genreDto);

        return returnValue;
    }

    public GenreResponseJson createGenre(GenreRequestJson genreRequestJson) throws ResponseStatusException {
        if(genreRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        GenreDto genreDto = new GenreDto();
        genreDto = GenreMapper.requestToDto(genreRequestJson);

        Genre newEntity = new Genre();
        newEntity = GenreMapper.dtoToGenre(genreDto);
        newEntity.setPublicId(PublicIdGenerator.generateId(30));

        Genre savedEntity = genreRepository.save(newEntity);
        GenreDto savedEntityDto = GenreMapper.genreToDto(savedEntity);
        GenreResponseJson returnValue = new GenreResponseJson();
        returnValue = GenreMapper.dtoToResponse(savedEntityDto);

        return returnValue;
    }

    public GenreResponseJson updateGenre(GenreRequestJson genreRequestJson) throws ResponseStatusException {
        if(genreRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        Optional<Genre> genreToUpdate = genreRepository.findByPublicIdAndDeletedAtIsNull(genreRequestJson.getPublicId());

        if(genreToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + genreRequestJson.getPublicId());
        }

        Genre genre = new Genre();
        GenreDto genreDto = new GenreDto();
        genreDto = GenreMapper.requestToDto(genreRequestJson);
        genre = GenreMapper.dtoToGenre(genreDto);

        genre.setId(genreToUpdate.get().getId());
        Genre updatedGenre = genreRepository.save(genre);

        GenreDto savedGenreDto = new GenreDto() ;
        savedGenreDto = GenreMapper.genreToDto(updatedGenre);
        GenreResponseJson returnValue = GenreMapper.dtoToResponse(savedGenreDto);

        return returnValue;
    }

    public String removeGenreByPublicId(String publicId) throws ResponseStatusException {
        if(publicId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The identifier is null. Give a valid identifier number!"
            );
        }

        Optional<Genre> genre = genreRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(genre.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with ID: " + publicId);
        }

        genreRepository.delete(genre.get());

        return "Genre with identifier: " + publicId + " successfully deleted!";
    }

    public Optional<Genre> getGenreByPublicId(String publicId) {
        return genreRepository.findByPublicIdAndDeletedAtIsNull(publicId);
    }
}
