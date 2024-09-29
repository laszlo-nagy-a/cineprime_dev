package com.homeproject.cineprime.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicId;
    @Column(
            length = 500
    )
    @NotBlank
    @Size(max = 500)
    private String title;
    @Column(
            length = 1024
    )
    @Size(max = 1024)
    private String description;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private Byte pg;
    @Column(name = "playtime_min")
    private Short playTimeMin;
    @Column(name = "last_modified_at")
    private Timestamp lastModifiedAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "writer_movie",
            joinColumns = {
                    @JoinColumn(
                            name = "movie_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "writer_id",
                            nullable = false
                    )
            }
    )
    private List<Writer> writerList;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "director_movie",
            joinColumns = {
                    @JoinColumn(
                            name = "movie_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "director_id",
                            nullable = false
                    )
            }
    )
    private List<Director> directorList;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "star_movie",
            joinColumns = {
                    @JoinColumn(
                            name = "movie_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "star_id",
                            nullable = false
                    )
            }
    )
    private List<Star> starList;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "genre_movie",
            joinColumns = {
                    @JoinColumn(
                            name = "movie_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "genre_id",
                            nullable = false
                    )
            }
    )
    private List<Genre> genreList;
    private Date deletedAt;
}
