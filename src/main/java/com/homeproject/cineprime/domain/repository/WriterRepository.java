package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WriterRepository extends JpaRepository<Writer, Long> {
    Optional<Writer> findByPublicId(String publicId);
}
