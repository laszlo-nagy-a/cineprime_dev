package com.homeproject.cineprime.repositories;

import com.homeproject.cineprime.models.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long> {
}
