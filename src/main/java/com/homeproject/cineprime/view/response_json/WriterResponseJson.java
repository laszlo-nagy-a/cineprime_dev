package com.homeproject.cineprime.view.response_json;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WriterResponseJson {
    public String publicId;
    public String firstName;
    public String lastName;
    public int age;
    public Date birthDate;
}
