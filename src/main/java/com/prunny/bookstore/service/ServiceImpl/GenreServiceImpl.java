package com.prunny.bookstore.service.ServiceImpl;

import com.prunny.bookstore.core.BlankCredentialsException;
import com.prunny.bookstore.core.InvalidCredentialsException;
import com.prunny.bookstore.dto.request.GenreCreationRequest;
import com.prunny.bookstore.dto.request.GenreUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.enums.ResponseCode;
import com.prunny.bookstore.model.Book;
import com.prunny.bookstore.model.Genre;
import com.prunny.bookstore.repository.BookRepository;
import com.prunny.bookstore.repository.GenreRepository;
import com.prunny.bookstore.service.GenreService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {
    private final  GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public ResponseEntity<ResponseDto<Boolean>> createGenre(GenreCreationRequest genreCreationRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();

        if(StringUtils.isBlank(genreCreationRequest.getGenreName())){
            throw new BlankCredentialsException("Genre name cannot be blank");
        }
        if(StringUtils.isBlank(genreCreationRequest.getDescription())){
            throw new BlankCredentialsException("Description cannot be blank");
        }
        if(StringUtils.isBlank(genreCreationRequest.getCreationDate())){
            throw new BlankCredentialsException("Creation date cannot be blank");
        }
        LocalDateTime creationDate;
        if (isValidDateTimeString(genreCreationRequest.getCreationDate())) {
            creationDate = parseLocalDateTime(genreCreationRequest.getCreationDate());
        } else {
            creationDate = LocalDateTime.now();
        }
        genreRepository.save(Genre.builder()
                .genreName(genreCreationRequest.getGenreName())
                .description(genreCreationRequest.getDescription())
                .creationDate(creationDate)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build());

        resp.setData(true);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> updateGenre(GenreUpdateRequest genreUpdateRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();

        if(Objects.isNull(genreUpdateRequest.getGenreId())){
            throw new BlankCredentialsException("Genre id cannot be blank");
        }
        if(StringUtils.isBlank(genreUpdateRequest.getGenreName())){
            throw new BlankCredentialsException("Genre name cannot be blank");
        }
        if(StringUtils.isBlank(genreUpdateRequest.getDescription())){
            throw new BlankCredentialsException("Description cannot be blank");
        }
        if(StringUtils.isBlank(genreUpdateRequest.getCreationDate())){
            throw new BlankCredentialsException("Creation date cannot be blank");
        }
        LocalDateTime creationDate = parseLocalDateTime(genreUpdateRequest.getCreationDate());

        Genre genre = genreRepository.findById(genreUpdateRequest.getGenreId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid author id"));

        genre.setGenreName(genreUpdateRequest.getGenreName());
        genre.setDescription(genreUpdateRequest.getDescription());
        genre.setCreationDate(creationDate);

        genreRepository.save(genre);

        resp.setData(true);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Genre>> getById(Long id) {
        ResponseDto<Genre> resp = new ResponseDto<>();
        if(Objects.isNull(id)){
            throw new BlankCredentialsException("Genre id cannot be blank");
        }
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid genre id"));

        resp.setData(genre);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Page<Genre>>> getAllGenre(int page, int pageRecord) {
        ResponseDto<Page<Genre>> resp = new ResponseDto<>();
        Pageable pageable = PageRequest.of(page, pageRecord);
        Page<Genre> genre = genreRepository.findAllByOrderByIdDesc(pageable);
        resp.setData(genre);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(IdRequest idRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();
        if(Objects.isNull(idRequest.getId())){
            throw new BlankCredentialsException("Genre id cannot be blank");
        }
        if (idRequest.getId() <= 0){
            throw new InvalidCredentialsException("Invalid genre id");
        }
        Genre genre = genreRepository.findById(idRequest.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid genre id"));
        List<Book> books = bookRepository.findByAuthorId(idRequest.getId());
        if (!books.isEmpty()) {
            bookRepository.deleteAll(books);
        }
        List<Book> book = bookRepository.findByGenreId(idRequest.getId());
        if (!book.isEmpty()) {
            bookRepository.deleteAll(book);
        }
        genreRepository.delete(genre);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        resp.setData(true);
        return new ResponseEntity<>(resp, HttpStatus.NO_CONTENT);
    }
    private LocalDateTime parseLocalDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, ISO_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCredentialsException("Invalid creation date format");
        }
    }
    private boolean isValidDateTimeString(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, ISO_DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
