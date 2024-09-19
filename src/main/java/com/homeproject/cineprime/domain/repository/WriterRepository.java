package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Writer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface WriterRepository extends JpaRepository<Writer, Long>, AbstarctEntityRepository<Writer> {
    List<Writer> findByDeletedAtIsNullAndPersonData_FirstNameContains(Pageable pageConfig, String search);
    List<Writer> findByDeletedAtIsNullAndPersonData_LastNameContains(Pageable pageConfig, String search);
    List<Writer> findByDeletedAtIsNullAndPersonData_AgeEquals(Pageable pageConfig, Short aShort);
    List<Writer> findByDeletedAtIsNullAndPersonData_BirthdateEquals(Pageable pageConfig, Date parse);
}
