package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    public Optional<Director> findByPublicId(String publicId);
}
