package com.homeproject.cineprime.models;

import jakarta.persistence.*;
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
    @Column(
            length = 500,
            nullable = false
    )
    private String title;
    @Column(name = "release_date")
    private Date releaseDate;
    private Byte pg;
    @Column(name = "playtime_min")
    private Short playtimeMin;
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
}
