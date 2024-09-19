package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, AbstarctEntityRepository<Movie> {
    List<Movie> findByTitleAndDeletedAtIsNull(Pageable pageConfig, String search);
    List<Movie> findByDescriptionContainsAndDeletedAtIsNull(Pageable pageConfig, String search);
    List<Movie> findByReleaseDateAndDeletedAtIsNull(Pageable pageConfig, Date parse);
    List<Movie> findByPgAndDeletedAtIsNull(Pageable pageConfig, Byte aByte);
    List<Movie> findByPlayTimeMinAndDeletedAtIsNull(Pageable pageConfig, Short aShort);
}
