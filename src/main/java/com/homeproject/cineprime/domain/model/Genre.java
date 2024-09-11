package com.homeproject.cineprime.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicId;
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
    private Date deletedAt;
}
