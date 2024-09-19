package com.homeproject.cineprime.logic.service.search;

import com.homeproject.cineprime.domain.model.Star;
import com.homeproject.cineprime.domain.repository.StarRepository;
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
public class StarSearchService {
    private final StarRepository starRepository;

    public StarSearchService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public List<Star> searchByTypeAndSearch(String type, String search, Optional<Integer> pageSizeOptional, Optional<Integer> pageNumberOptional) throws ResponseStatusException {
        Integer pageSize = pageSizeOptional.orElse(10);
        Integer pageNumber = pageNumberOptional.orElse(0);
        Pageable pageConfig = PageRequest.of(pageNumber, pageSize);
        List<Star> resultSet = new ArrayList<>();

        if(type == null) {
            resultSet.addAll(starRepository.findByDeletedAtIsNull(pageConfig));
            return resultSet;
        }

        try {
            switch (type) {
                case SearchType.FIRST_NAME:
                    resultSet.addAll(starRepository
                            .findByDeletedAtIsNullAndPersonData_FirstNameContains(pageConfig, search));
                    break;
                case SearchType.LAST_NAME:
                    resultSet.addAll(starRepository
                            .findByDeletedAtIsNullAndPersonData_LastNameContains(pageConfig, search));
                    break;
                case SearchType.AGE:
                    resultSet.addAll(
                            starRepository
                                    .findByDeletedAtIsNullAndPersonData_AgeEquals(pageConfig, Short.valueOf(search)));
                    break;
                case SearchType.BIRTH_DATE:
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    resultSet.addAll(starRepository
                            .findByDeletedAtIsNullAndPersonData_BirthdateEquals(pageConfig, formatter.parse(search)));
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Not compatible type! Try with these type values: "
                                    .concat(SearchType.FIRST_NAME + " OR ")
                                    .concat(SearchType.FIRST_NAME + " OR ")
                                    .concat(SearchType.AGE + " OR ")
                                    .concat(SearchType.BIRTH_DATE));
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
