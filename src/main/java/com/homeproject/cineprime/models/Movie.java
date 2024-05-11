package com.homeproject.cineprime.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    @Column(name = "release_date")
    private Date releaseDate;
    private Integer pg;
    @Column(name = "playtime_min")
    private Integer playtimeMin;
    @Column(name = "last_modified_at")
    private Timestamp lastModifiedAt;
    @ManyToMany
    @JoinTable (
            name = "writer_movie",
            joinColumns = {
                    @JoinColumn(name = "movie_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "writer_id")
            }
    )
    private List<Writer> writerList;
    @ManyToMany
    @JoinTable (
            name = "director_movie",
            joinColumns = {
                    @JoinColumn(name = "movie_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "director_id")
            }
    )
    private List<Director> directorList;
    @ManyToMany
    @JoinTable (
            name = "star_movie",
            joinColumns = {
                    @JoinColumn(name = "movie_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "star_id")
            }
    )
    private List<Star> starList;
    @ManyToMany
    @JoinTable (
            name = "genre_movie",
            joinColumns = {
                    @JoinColumn(name = "movie_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            }
    )
    private List<Genre> genreList;
}
