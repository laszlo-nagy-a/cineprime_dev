package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long>, AbstarctEntityRepository<Genre> {

}
