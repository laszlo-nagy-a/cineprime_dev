package com.homeproject.cineprime.domain.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbstarctEntityRepository<T> {
    public Optional<T> findByPublicIdAndDeletedAtIsNull(String publicId);
    public List<T> findByDeletedAtIsNull(Pageable config);

}
