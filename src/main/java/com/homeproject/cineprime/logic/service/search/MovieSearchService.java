package com.homeproject.cineprime.logic.service.search;

import com.homeproject.cineprime.domain.model.Movie;
import com.homeproject.cineprime.domain.repository.MovieRepository;
import com.homeproject.cineprime.logic.util.SearchType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieSearchService {

    private final MovieRepository movieRepository;

    public MovieSearchService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> searchByTypeAndSearch(String type, String search, Optional<Integer> pageSizeOptional, Optional<Integer> pageNumberOptional) throws ResponseStatusException {
        Integer pageSize = pageSizeOptional.orElse(10);
        Integer pageNumber = pageNumberOptional.orElse(0);
        Pageable pageConfig = PageRequest.of(pageNumber, pageSize);
        List<Movie> resultSet = new ArrayList<>();

        if(type == null) {
            resultSet.addAll(movieRepository.findByDeletedAtIsNull(pageConfig));
            return resultSet;
        }

        try {
            switch (type) {
                case SearchType.TITLE:
                    resultSet.addAll(movieRepository
                            .findByTitleAndDeletedAtIsNull(pageConfig, search));
                    break;
                case SearchType.DESCRIPTION:
                    resultSet.addAll(movieRepository
                            .findByDescriptionContainsAndDeletedAtIsNull(pageConfig, search));
                    break;
                case SearchType.RELEASE_DATE:
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    resultSet.addAll(movieRepository
                            .findByReleaseDateAndDeletedAtIsNull(pageConfig, formatter.parse(search)));
                    break;
                case SearchType.PG:
                    resultSet.addAll(movieRepository
                            .findByPgAndDeletedAtIsNull(pageConfig, Byte.valueOf(search)));
                    break;
                case SearchType.PLAY_TIME_MIN:
                    resultSet.addAll(movieRepository
                            .findByPlayTimeMinAndDeletedAtIsNull(pageConfig, Short.valueOf(search)));
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Not compatible type! Try with these type values: "
                                    .concat(SearchType.TITLE + " OR ")
                                    .concat(SearchType.RELEASE_DATE + " OR ")
                                    .concat(SearchType.PG + " OR ")
                                    .concat(SearchType.PLAY_TIME_MIN));
            }
        } catch (ParseException parseExceptionx) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The date format is incorrect! Try with this format: 'yyyy-mm-dd' ");
        } catch (NumberFormatException numberFormatException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The number format is not correct, try with integer number.");
        }
        return resultSet;
    }
}
