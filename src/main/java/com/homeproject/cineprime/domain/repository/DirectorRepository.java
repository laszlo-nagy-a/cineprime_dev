package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Director;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface DirectorRepository extends PagingAndSortingRepository<Director, Long>, AbstarctEntityRepository<Director>, JpaRepository<Director, Long> {
    List<Director> findByDeletedAtIsNullAndPersonData_FirstNameContains(Pageable pageConfig, String firstname);
    List<Director> findByDeletedAtIsNullAndPersonData_LastNameContains(Pageable pageConfig, String search);
    List<Director> findByDeletedAtIsNullAndPersonData_AgeEquals(Pageable pageConfig, Short search);
    List<Director> findByDeletedAtIsNullAndPersonData_BirthdateEquals(Pageable pageConfig, Date search);
}
