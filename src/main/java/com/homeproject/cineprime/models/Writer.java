package com.homeproject.cineprime.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Writer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToMany(
                fetch = FetchType.LAZY,
                mappedBy = "writerList"
        )
        // TODO: Lazy nem működik, ez esetben DTO, vagy használjuk a JsonIdentifyInfo annotációt esetleg?
        private List<Movie> movieList;
        @Embedded
        private PersonData personData;
}
