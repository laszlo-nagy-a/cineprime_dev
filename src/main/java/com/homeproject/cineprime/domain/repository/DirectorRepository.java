package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long>, AbstarctEntityRepository<Director> {
    List<Director> findByPersonData_FirstNameContains(String firstname);
    List<Director> findByPersonData_LastNameContains(String search);
    List<Director> findByPersonData_AgeEquals(Short search);
    List<Director> findByPersonData_BirthdateEquals(Date search);
}
