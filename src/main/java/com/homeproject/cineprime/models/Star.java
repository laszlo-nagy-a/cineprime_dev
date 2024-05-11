package com.homeproject.cineprime.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Star extends PersonData{

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToMany(mappedBy = "starList")
    private List<Movie> movieList;
}
