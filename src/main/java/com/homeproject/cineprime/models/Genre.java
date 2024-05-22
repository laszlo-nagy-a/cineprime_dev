package com.homeproject.cineprime.models;

import jakarta.persistence.*;
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
    @Column(
          length = 500,
          nullable = false
    )
    private String name;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "genreList"
    )
    private List<Movie> movieList;

}
