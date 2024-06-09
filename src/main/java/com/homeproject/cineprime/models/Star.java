package com.homeproject.cineprime.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "starList"
    )
    private List<Movie> movieList;
    @Embedded
    @Valid
    private PersonData personData;
}
