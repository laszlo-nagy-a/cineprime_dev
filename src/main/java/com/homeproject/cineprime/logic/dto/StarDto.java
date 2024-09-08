package com.homeproject.cineprime.logic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StarDto {
    private Long id;
    private String publicId;
    private String firstName;
    private String lastName;
    private Short age;
    private Date bithDate;
}