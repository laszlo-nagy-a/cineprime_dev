package com.homeproject.cineprime.logic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WriterDto extends AbastractEntityDto {
    private String firstName;
    private String lastName;
    private Short age;
    private Date bithDate;
}
