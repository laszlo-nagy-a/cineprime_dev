package com.homeproject.cineprime.view.request_json;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WriterRequestJson {
    private String publicId;
    private String firstName;
    private String lastName;
    private short age;
    private Date birthDate;
}
