package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long>, AbstarctEntityRepository<Star> {

}
