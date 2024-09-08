package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByPublicId(String publicId);
}
