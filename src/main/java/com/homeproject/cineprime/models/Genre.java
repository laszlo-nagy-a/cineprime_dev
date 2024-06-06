package com.homeproject.cineprime.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Genre{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(
            min = 1,
            max = 500
    )
    private String name;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "genreList"
    )
    private List<Movie> movieList;

}
