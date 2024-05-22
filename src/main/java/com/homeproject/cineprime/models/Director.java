package com.homeproject.cineprime.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "directorList"
            )
    private List<Movie> movieList;
    @Embedded
    private PersonData personData;

}
