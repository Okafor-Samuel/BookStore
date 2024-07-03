package com.prunny.bookstore.repository;

import com.prunny.bookstore.enums.Language;
import com.prunny.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookRepository  extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByGenreId(Long genreId);
    Page<Book> findAllByOrderByIdDesc(Pageable pageable);
    Page<Book> findByPublishedDateBetween(Date startDate, Date endDate, Pageable pageable);
    Page<Book> findByPriceBetween(Double startPrice, Double endPrice, Pageable pageable);
    Page<Book> findByLanguage(Language language, Pageable pageable);
    Optional<Book> findByTitle(String title);
}
