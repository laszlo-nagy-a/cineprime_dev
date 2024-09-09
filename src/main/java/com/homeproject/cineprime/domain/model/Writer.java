package com.homeproject.cineprime.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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
        private String publicId;
        @ManyToMany(
                fetch = FetchType.LAZY,
                mappedBy = "writerList"
        )
        private List<Movie> movieList;
        @Embedded
        @Valid
        private PersonData personData;
}
