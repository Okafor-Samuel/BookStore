package com.prunny.bookstore.service;

import com.prunny.bookstore.dto.request.AuthorCreationRequest;
import com.prunny.bookstore.dto.request.AuthorUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorService {
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> createAuthor(AuthorCreationRequest authorCreationRequest);
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> updateAuthor(AuthorUpdateRequest authorUpdateRequest);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Author>> getById(Long id);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Page<Author>>> getAllAuthors(int page, int pageRecord);
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> delete(IdRequest idRequest);
}
