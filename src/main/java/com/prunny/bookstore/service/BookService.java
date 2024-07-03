package com.prunny.bookstore.service;

import com.prunny.bookstore.dto.request.BookCreationRequest;
import com.prunny.bookstore.dto.request.BookUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface BookService {
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> createBook(BookCreationRequest bookCreationRequest);
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> updateBook(BookUpdateRequest bookUpdateRequest);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Book>> getById(Long id);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Book>> getByTitle(String title);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Page<Book>>> getByPublishedDate(String startDate, String endDate, int page, int pageRecord);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Page<Book>>> getByPrice(String startPrice, String endPrice, int page, int pageRecord);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Page<Book>>> getByLanguage(String language, int page, int pageRecord);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Page<Book>>> getAllBook(int page, int pageRecord);
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> delete(IdRequest idRequest);
}
