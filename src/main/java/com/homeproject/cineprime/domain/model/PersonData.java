package com.homeproject.cineprime.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Embeddable
public class PersonData {

    @Column(
            name = "first_name"
    )
    @NotBlank
    private String firstName;
    @Column(
            name = "last_name"
    )
    @NotBlank
    private String lastName;
    private Short age;
    private Date birthdate;
}
