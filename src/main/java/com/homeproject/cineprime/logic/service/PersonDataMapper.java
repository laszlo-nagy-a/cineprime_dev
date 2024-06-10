package com.homeproject.cineprime.logic.service;

import com.homeproject.cineprime.domain.model.PersonData;

public class PersonDataMapper {
    public static String personDataToName(PersonData personData) {
        if(personData == null) {
            throw new IllegalArgumentException("Movie cannot be null!");
        }

        return personData.getFirstName() + " " + personData.getLastName();
    }
}
