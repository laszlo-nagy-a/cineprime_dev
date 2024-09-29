package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.domain.model.Director;
import com.homeproject.cineprime.domain.model.PersonData;
import com.homeproject.cineprime.logic.dto.DirectorDto;
import com.homeproject.cineprime.view.request_json.DirectorRequestJson;
import com.homeproject.cineprime.view.response_json.DirectorResponseJson;
import org.springframework.stereotype.Service;

@Service
public class DirectorMapper {

    public static DirectorDto directorToDto(Director director) {

        if(director == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        PersonData personData = director.getPersonData();

        DirectorDto returnValue = new DirectorDto();
        returnValue.setId(director.getId());
        returnValue.setPublicId(director.getPublicId());
        returnValue.setFirstName(personData.getFirstName());
        returnValue.setLastName(personData.getLastName());
        returnValue.setAge(personData.getAge());
        returnValue.setBithDate(personData.getBirthdate());

        return returnValue;
    }

    public static DirectorResponseJson dtoToResponse(DirectorDto directorDto) {

        if(directorDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        DirectorResponseJson returnValue = new DirectorResponseJson();
        returnValue.setPublicId(directorDto.getPublicId());
        returnValue.setFirstName(directorDto.getFirstName());
        returnValue.setLastName(directorDto.getLastName());
        returnValue.setAge(directorDto.getAge());
        returnValue.setBirthDate(directorDto.getBithDate());

        return returnValue;

    }

    public static DirectorDto requestToDto(DirectorRequestJson directorRequestJson) {

        if(directorRequestJson == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        DirectorDto returnValue = new DirectorDto();
        returnValue.setPublicId(directorRequestJson.getPublicId());
        returnValue.setFirstName(directorRequestJson.getFirstName());
        returnValue.setLastName(directorRequestJson.getLastName());
        returnValue.setAge(directorRequestJson.getAge());
        returnValue.setBithDate(directorRequestJson.getBirthDate());

        return returnValue;

    }

    public static Director dtoToDirector(DirectorDto directorDto) {

        if(directorDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        Director returnValue = new Director();
        returnValue.setId(directorDto.getId());
        returnValue.setPublicId(directorDto.getPublicId());
        returnValue.setPersonData(new PersonData(
                directorDto.getFirstName(),
                directorDto.getLastName(),
                directorDto.getAge(),
                directorDto.getBithDate()
        ));

        return returnValue;
    }
}
