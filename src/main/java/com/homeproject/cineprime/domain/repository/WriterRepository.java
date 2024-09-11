package com.homeproject.cineprime.domain.repository;

import com.homeproject.cineprime.domain.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long>, AbstarctEntityRepository<Writer> {

}
