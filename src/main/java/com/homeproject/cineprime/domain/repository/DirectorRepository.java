package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long>, AbstarctEntityRepository<Director> {

}
