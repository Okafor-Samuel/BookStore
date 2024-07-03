package com.prunny.bookstore.service;

import com.prunny.bookstore.dto.request.GenreCreationRequest;
import com.prunny.bookstore.dto.request.GenreUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface GenreService {
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> createGenre(GenreCreationRequest genreCreationRequest);
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> updateGenre(GenreUpdateRequest genreUpdateRequest);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Genre>> getById(Long id);
    @Transactional(readOnly = true)
    ResponseEntity<ResponseDto<Page<Genre>>> getAllGenre(int page, int pageRecord);
    @Transactional
    ResponseEntity<ResponseDto<Boolean>> delete(IdRequest idRequest);
}
