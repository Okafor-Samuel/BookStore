package com.prunny.bookstore.repository;

import com.prunny.bookstore.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(Long id);
    Page<Author> findAllByOrderByIdDesc(Pageable pageable);
}
