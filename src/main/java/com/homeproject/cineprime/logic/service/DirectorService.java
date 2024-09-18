package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.Director;
import com.homeproject.cineprime.domain.repository.DirectorRepository;
import com.homeproject.cineprime.logic.dto.DirectorDto;
import com.homeproject.cineprime.logic.mapper.DirectorMapper;
import com.homeproject.cineprime.logic.util.PublicIdGenerator;
import com.homeproject.cineprime.logic.util.SearchType;
import com.homeproject.cineprime.view.request_json.DirectorRequestJson;
import com.homeproject.cineprime.view.response_json.DirectorResponseJson;
import com.sun.jdi.InvalidTypeException;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional(readOnly = true)
    public List<DirectorResponseJson> getAllDirectorResponseJson(String type, String search, Optional<Integer> pageNumber, Optional<Integer> pageSize) throws HttpStatusCodeException {

        List<Director> resultSet = directorSearchByType(type, search, pageNumber, pageSize);

        if(resultSet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Directors not found with these conditions, try with another!" );
        }

        // TODO: strema filterrel megoldani, a keresést dinamikusan -> type változó alapján meg kell határozni, hogy melyik field alapján kell a keywordot futtatni
        List<DirectorDto> allDirectorDto = resultSet
                .stream()
                .map(DirectorMapper::directorToDto)
                .toList();

        return allDirectorDto
                .stream()
                .map(DirectorMapper::dtoToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DirectorResponseJson getDirectorResponseJsonByPublicId(String publicId) throws ResponseStatusException {

        if(publicId == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                """
                The given ID is NULL or empty string, 
                try with another ID! Given ID: 
                """ + publicId);
    }

        Optional<Director> director = directorRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(director.isEmpty() || director.get().getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + publicId);
        }

        DirectorDto directorDto = new DirectorDto();
        directorDto = DirectorMapper.directorToDto(director.get());
        DirectorResponseJson returnValue = DirectorMapper.dtoToResponse(directorDto);

        return returnValue;
    }

    public DirectorResponseJson createDirector(DirectorRequestJson directorRequestJson) throws ResponseStatusException {

        if(directorRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        DirectorDto directorDto = new DirectorDto();
        directorDto = DirectorMapper.requestToDto(directorRequestJson);

        Director director = new Director();
        director = DirectorMapper.dtoToDirector(directorDto);
        director.setPublicId(PublicIdGenerator.generateId(30));

        Director savedDirector = directorRepository.save(director);

        DirectorDto savedDirectorDto = DirectorMapper.directorToDto(savedDirector);
        DirectorResponseJson returnValue = DirectorMapper.dtoToResponse(savedDirectorDto);

        return returnValue;
    }

    public DirectorResponseJson updateDirector(DirectorRequestJson directorRequestJson) throws ResponseStatusException {

        if(directorRequestJson == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given object not compatible type or null.");
        }

        Optional<Director> directorToUpdate = directorRepository.findByPublicIdAndDeletedAtIsNull(directorRequestJson.getPublicId());

        if(directorToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + directorRequestJson.getPublicId());
        }

        Director director = new Director();
        DirectorDto directorDto = new DirectorDto();
        directorDto = DirectorMapper.requestToDto(directorRequestJson);
        director = DirectorMapper.dtoToDirector(directorDto);

        director.setId(directorToUpdate.get().getId());
        Director savedDirector = directorRepository.save(director);

        DirectorDto savedDirectorDto = new DirectorDto();
        savedDirectorDto = DirectorMapper.directorToDto(savedDirector);
        DirectorResponseJson returnValue = new DirectorResponseJson();
        returnValue = DirectorMapper.dtoToResponse(savedDirectorDto);


        return returnValue;
    }

    public String removeDirectorByPublidId(String publicId) throws ResponseStatusException {

        if(publicId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The identifier is null. Give a valid identifier number!"
            );
        }

        Optional<Director> director = directorRepository.findByPublicIdAndDeletedAtIsNull(publicId);

        if(director.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director not found with ID: " + publicId);
        }

        Director directorToLogicallyDelete = director.get();
        directorToLogicallyDelete.setDeletedAt(Date.from(Instant.now()));

        directorRepository.save(directorToLogicallyDelete);

        return "Director with identifier: " + publicId + " successfully deleted!";
    }

    public Optional<Director> findByPublicId(String directorPublicId) {
        return directorRepository.findByPublicIdAndDeletedAtIsNull(directorPublicId);
    }

    public List<Director> directorSearchByType (String type, String search, Optional<Integer> pageNumberOptional, Optional<Integer> pageSizeOptinal) {

        Integer pageNumber = pageNumberOptional.orElse(0);
        Integer pageSize = pageSizeOptinal.orElse(10);

        Pageable pageConfig = PageRequest.of(pageNumber, pageSize);

        List<Director> resultSet = new ArrayList<>();

        if(type == null) {
            resultSet.addAll(directorRepository.findByDeletedAtIsNull(pageConfig));
            return resultSet;
        }

        return searchByTypeAndSearch(type, search, pageConfig);
    }

    public List<Director> searchByTypeAndSearch(String type, String search, Pageable pageConfig) throws ResponseStatusException {
        List<Director> resultSet = new ArrayList<>();
        try {
            switch (type) {
                case SearchType.FIRST_NAME:
                    resultSet.addAll(directorRepository.findByDeletedAtIsNullAndPersonData_FirstNameContains(pageConfig, search));
                    break;

                case SearchType.LAST_NAME:
                    resultSet.addAll(directorRepository.findByDeletedAtIsNullAndPersonData_LastNameContains(pageConfig, search));
                    break;

                case SearchType.AGE:
                    resultSet.addAll(
                            directorRepository.findByDeletedAtIsNullAndPersonData_AgeEquals(pageConfig, Short.valueOf(search)));
                    break;

                case SearchType.BIRTH_DATE:
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    resultSet.addAll(directorRepository.findByDeletedAtIsNullAndPersonData_BirthdateEquals(pageConfig, formatter.parse(search)));
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The date format is incorrect! Try with this format: 'yyyy-mm-dd' ");
        } catch (NumberFormatException numberFormatException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number format is not correct, try with integer number.");
        }

        return resultSet;
    }
}
