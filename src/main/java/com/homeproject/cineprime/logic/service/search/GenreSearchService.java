package com.homeproject.cineprime.logic.service.search;

import com.homeproject.cineprime.domain.model.Genre;
import com.homeproject.cineprime.domain.repository.GenreRepository;
import com.homeproject.cineprime.logic.util.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GenreSearchService {

    @Autowired
    private final GenreRepository genreRepository;

    public GenreSearchService(GenreRepository genreRepositoy) {
        this.genreRepository = genreRepositoy;
    }

    public List<Genre> searhByTypeAndSearch(String type, String search, Optional<Integer> pageSizeOptional, Optional<Integer> pageNumberOptional) throws ResponseStatusException {
        Integer pageSize = pageSizeOptional.orElse(10);
        Integer pageNumber = pageNumberOptional.orElse(0);
        Pageable pageConfig = PageRequest.of(pageNumber, pageSize);
        List<Genre> resultSet = new ArrayList<>();

        if (type == null) {
            resultSet.addAll(genreRepository.findByDeletedAtIsNull(pageConfig));
            return resultSet;
        }

        // TODO: legyen ez is switch az egységesség miatt, vagy optimalizáltabb így?
        if (type.equals(SearchType.NAME)) {
            resultSet.addAll(genreRepository
                    .findByNameContainsAndDeletedAtIsNull(pageConfig, search));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not compatible type! Try with these type values: "
                            .concat(SearchType.NAME + " OR "));
        }
        return resultSet;
    }
}
