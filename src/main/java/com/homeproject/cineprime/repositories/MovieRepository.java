package com.homeproject.cineprime.repositories;

import com.homeproject.cineprime.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
