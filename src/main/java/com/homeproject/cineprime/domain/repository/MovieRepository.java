package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, AbstarctEntityRepository<Movie> {

}
