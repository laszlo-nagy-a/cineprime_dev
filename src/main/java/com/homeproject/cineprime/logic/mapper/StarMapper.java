package com.homeproject.cineprime.logic.mapper;

import com.homeproject.cineprime.domain.model.PersonData;
import com.homeproject.cineprime.domain.model.Star;
import com.homeproject.cineprime.logic.dto.StarDto;
import com.homeproject.cineprime.view.request_json.StarRequestJson;
import com.homeproject.cineprime.view.response_json.StarResponseJson;

public class StarMapper {
    public static StarDto starToDto(Star star) {

        if(star == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        PersonData personData = star.getPersonData();

        StarDto returnValue = new StarDto();
        returnValue.setId(star.getId());
        returnValue.setPublicId(star.getPublicId());
        returnValue.setFirstName(personData.getFirstName());
        returnValue.setLastName(personData.getLastName());
        returnValue.setAge(personData.getAge());
        returnValue.setBithDate(personData.getBirthdate());

        return returnValue;
    }

    public static StarResponseJson dtoToResponse(StarDto starDto) {

        if(starDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        StarResponseJson returnValue = new StarResponseJson();
        returnValue.setPublicId(starDto.getPublicId());
        returnValue.setName(starDto.getFirstName() + " " + starDto.getLastName());
        returnValue.setAge(starDto.getAge());
        returnValue.setBirthDate(starDto.getBithDate());

        return returnValue;

    }

    public static StarDto requestToDto(StarRequestJson starRequestJson) {

        if(starRequestJson == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        StarDto returnValue = new StarDto();
        returnValue.setPublicId(starRequestJson.getPublicId());
        returnValue.setFirstName(starRequestJson.getFirstName());
        returnValue.setLastName(starRequestJson.getLastName());
        returnValue.setAge(starRequestJson.getAge());
        returnValue.setBithDate(starRequestJson.getBirthDate());

        return returnValue;

    }

    public static Star dtoToStar(StarDto starDto) {

        if(starDto == null) {
            throw new IllegalArgumentException("Given args are not compatible.");
        }

        Star returnValue = new Star();
        returnValue.setId(starDto.getId());
        returnValue.setPublicId(starDto.getPublicId());
        returnValue.setPersonData(new PersonData(
                starDto.getFirstName(),
                starDto.getLastName(),
                starDto.getAge(),
                starDto.getBithDate()
        ));

        return returnValue;
    }
}
