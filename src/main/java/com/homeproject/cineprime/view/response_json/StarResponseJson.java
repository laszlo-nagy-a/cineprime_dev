package com.homeproject.cineprime.view.response_json;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StarResponseJson {
    public String publicId;
    public String name;
    public int age;
    public Date birthDate;
}
