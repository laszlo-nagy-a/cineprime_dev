package com.homeproject.cineprime.repositories;

import com.homeproject.cineprime.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
