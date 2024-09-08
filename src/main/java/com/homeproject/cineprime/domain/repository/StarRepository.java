package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findByPublicId(String publicId);
}
