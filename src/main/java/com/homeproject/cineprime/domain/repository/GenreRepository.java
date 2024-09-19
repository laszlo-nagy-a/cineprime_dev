package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends AbstarctEntityRepository<Genre>, JpaRepository<Genre, Long> {
    List<Genre> findByNameContainsAndDeletedAtIsNull(Pageable pageConfig, String name);
}
