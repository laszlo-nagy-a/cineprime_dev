package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Star;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface StarRepository extends JpaRepository<Star, Long>, AbstarctEntityRepository<Star> {
    List<Star> findByDeletedAtIsNullAndPersonData_BirthdateEquals(Pageable pageConfig, Date parse);
    List<Star> findByDeletedAtIsNullAndPersonData_FirstNameContains(Pageable pageConfig, String search);
    List<Star> findByDeletedAtIsNullAndPersonData_LastNameContains(Pageable pageConfig, String search);
    List<Star> findByDeletedAtIsNullAndPersonData_AgeEquals(Pageable pageConfig, Short aShort);
}
