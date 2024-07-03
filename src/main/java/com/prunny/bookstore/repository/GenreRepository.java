package com.prunny.bookstore.repository;


import com.prunny.bookstore.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findById(Long id);
    Page<Genre> findAllByOrderByIdDesc(Pageable pageable);
}
