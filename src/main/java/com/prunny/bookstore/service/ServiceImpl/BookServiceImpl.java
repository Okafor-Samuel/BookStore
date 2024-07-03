package com.prunny.bookstore.service.ServiceImpl;

import com.prunny.bookstore.Util.DateUtil;
import com.prunny.bookstore.Util.PriceUtil;
import com.prunny.bookstore.core.BlankCredentialsException;
import com.prunny.bookstore.core.InvalidCredentialsException;
import com.prunny.bookstore.dto.request.BookCreationRequest;
import com.prunny.bookstore.dto.request.BookUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.enums.Language;
import com.prunny.bookstore.enums.ResponseCode;
import com.prunny.bookstore.model.Author;
import com.prunny.bookstore.model.Book;
import com.prunny.bookstore.model.Genre;
import com.prunny.bookstore.repository.AuthorRepository;
import com.prunny.bookstore.repository.BookRepository;
import com.prunny.bookstore.repository.GenreRepository;
import com.prunny.bookstore.service.BookService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public ResponseEntity<ResponseDto<Boolean>> createBook(BookCreationRequest bookCreationRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();

        if (StringUtils.isBlank(bookCreationRequest.getTitle())) {
            throw new BlankCredentialsException("Title cannot be blank");
        }
        if (StringUtils.isBlank(bookCreationRequest.getDescription())) {
            throw new BlankCredentialsException("Description cannot be blank");
        }
        if (StringUtils.isBlank(bookCreationRequest.getLanguage())) {
            throw new BlankCredentialsException("Language cannot be blank");
        }
        Language language;
        try{
            language = Language.valueOf(bookCreationRequest.getLanguage());
        }catch (Exception e){
            throw new InvalidCredentialsException("Language is invalid");
        }
        if (StringUtils.isBlank(bookCreationRequest.getPublishedDate())) {
            throw new BlankCredentialsException("Published date cannot be blank");
        }
        if (StringUtils.isBlank(bookCreationRequest.getIsbn())) {
            throw new BlankCredentialsException("ISBN cannot be blank");
        }
        if (StringUtils.isBlank(bookCreationRequest.getSynopsis())) {
            throw new BlankCredentialsException("Synopsis cannot be blank");
        }
        if (StringUtils.isBlank(bookCreationRequest.getBookCover())) {
            throw new BlankCredentialsException("Book cover cannot be blank");
        }
        if (StringUtils.isBlank(bookCreationRequest.getPrice())) {
            throw new BlankCredentialsException("Price cannot be blank");
        }
        if (Objects.isNull(bookCreationRequest.getAuthorId())) {
            throw new BlankCredentialsException("Author ID cannot be blank");
        }
        if (Objects.isNull(bookCreationRequest.getGenreId())) {
            throw new BlankCredentialsException("Genre ID cannot be blank");
        }
        try {
            Double price = Double.parseDouble(bookCreationRequest.getPrice());

        Date publishedDate = DateUtil.getUtilDate(bookCreationRequest.getPublishedDate(), "yyyy-MM-dd");


        Author author = authorRepository.findById(bookCreationRequest.getAuthorId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid author id"));

        Genre genre = genreRepository.findById(bookCreationRequest.getGenreId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid genre id"));

        Book book = Book.builder()
                .title(bookCreationRequest.getTitle())
                .description(bookCreationRequest.getDescription())
                .language(language)
                .publishedDate(publishedDate)
                .isbn(bookCreationRequest.getIsbn())
                .synopsis(bookCreationRequest.getSynopsis())
                .bookCover(bookCreationRequest.getBookCover())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .price(price)
                .author(author)
                .genre(genre)
                .build();

        bookRepository.save(book);

        }catch (NumberFormatException e) {
            throw new InvalidCredentialsException("Invalid price format");
        }
        resp.setData(true);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> updateBook(BookUpdateRequest bookUpdateRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();

        if(Objects.isNull(bookUpdateRequest.getBookId())){
            throw new BlankCredentialsException("Book id cannot be blank");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getTitle())) {
            throw new BlankCredentialsException("Title cannot be blank");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getDescription())) {
            throw new BlankCredentialsException("Description cannot be blank");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getLanguage())) {
            throw new BlankCredentialsException("Language cannot be blank");
        }
        Language language;
        try{
            language = Language.valueOf(bookUpdateRequest.getLanguage());
        }catch (Exception e){
            throw new InvalidCredentialsException("Language is invalid");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getPublishedDate())) {
            throw new BlankCredentialsException("Published date cannot be blank");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getIsbn())) {
            throw new BlankCredentialsException("ISBN cannot be blank");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getSynopsis())) {
            throw new BlankCredentialsException("Synopsis cannot be blank");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getBookCover())) {
            throw new BlankCredentialsException("Book cover cannot be blank");
        }
        if (StringUtils.isBlank(bookUpdateRequest.getPrice())) {
            throw new BlankCredentialsException("Price cannot be blank");
        }

        try {
            Double price = Double.parseDouble(bookUpdateRequest.getPrice());

            Date publishedDate = DateUtil.getUtilDate(bookUpdateRequest.getPublishedDate(), "yyyy-MM-dd");


            Book book = bookRepository.findById(bookUpdateRequest.getBookId())
                    .orElseThrow(() -> new InvalidCredentialsException("Invalid book id"));


            book.setTitle(bookUpdateRequest.getTitle());
            book.setDescription(bookUpdateRequest.getDescription());
            book.setLanguage(language);
            book.setPublishedDate(publishedDate);
            book.setIsbn(bookUpdateRequest.getIsbn());
            book.setSynopsis(bookUpdateRequest.getSynopsis());
            book.setBookCover(bookUpdateRequest.getBookCover());
            book.setPrice(price);

            bookRepository.save(book);

        }catch (NumberFormatException e) {
            throw new InvalidCredentialsException("Invalid price format");
        }
        resp.setData(true);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDto<Book>> getById(Long id) {
        ResponseDto<Book> resp = new ResponseDto<>();
        if(Objects.isNull(id)){
            throw new BlankCredentialsException("Book id cannot be blank");
        }
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid book id"));


        resp.setData(book);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Book>> getByTitle(String title) {
        ResponseDto<Book> resp = new ResponseDto<>();
        if(StringUtils.isBlank(title)){
            throw new BlankCredentialsException("Book title cannot be blank");
        }
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new InvalidCredentialsException("Book not found with the given title"));


        resp.setData(book);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Page<Book>>> getByPublishedDate(String startDate, String endDate, int page, int pageRecord) {
        ResponseDto<Page<Book>> resp = new ResponseDto<>();

        if (StringUtils.isBlank(startDate)) {
            throw new BlankCredentialsException("Start date cannot be blank");
        }
        if (StringUtils.isBlank(endDate)) {
            throw new BlankCredentialsException("End date cannot be blank");
        }

        LocalDate localStartDate = DateUtil.getLocalDate(startDate);
        LocalDate localStopDate = DateUtil.getLocalDate(endDate);
        LocalDate now = LocalDate.now();

        if (localStartDate.isAfter(now)) {
            throw new InvalidCredentialsException("Start date cannot be after today");
        }
        if (localStopDate.isAfter(now)) {
            throw new InvalidCredentialsException("End date cannot be after today");
        }
        if (localStopDate.isBefore(localStartDate)) {
            throw new InvalidCredentialsException("End date cannot be before start date");
        }

        Date start = DateUtil.getUtilDate(startDate, "yyyy-MM-dd");
        Date end = DateUtil.getUtilDate(endDate, "yyyy-MM-dd");
        PageRequest pageRequest = PageRequest.of(page, pageRecord);
        Page<Book> books = bookRepository.findByPublishedDateBetween(start, end, pageRequest);

        resp.setData(books);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Page<Book>>> getByPrice(String startPrice, String endPrice, int page, int pageRecord) {
        ResponseDto<Page<Book>> resp = new ResponseDto<>();

        if (StringUtils.isBlank(startPrice)) {
            throw new InvalidCredentialsException("Start price cannot be blank");
        }
        if (StringUtils.isBlank(endPrice)) {
            throw new InvalidCredentialsException("End price cannot be blank");
        }

        try {
            Double startPriceDouble = Double.parseDouble(startPrice);
            Double endPriceDouble = Double.parseDouble(endPrice);

            PriceUtil.validatePrices(startPriceDouble, endPriceDouble);

            PageRequest pageRequest = PageRequest.of(page, pageRecord);
            Page<Book> books = bookRepository.findByPriceBetween(startPriceDouble, endPriceDouble, pageRequest);

            resp.setData(books);
            resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
            resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.OK);

        } catch (NumberFormatException e) {
            throw new InvalidCredentialsException("Invalid price format");
        } catch (PriceUtil.InvalidPriceException e) {
            resp.setStatusCode(ResponseCode.BAD_REQUEST.getCode());
            resp.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<Page<Book>>> getByLanguage(String language, int page, int pageRecord) {
        ResponseDto<Page<Book>> resp = new ResponseDto<>();
        if(Strings.isBlank(language)){
            throw new BlankCredentialsException("Language cannot be blank");
        }
        List<Language> languageList = Arrays.asList(Language.values());
        if (languageList.stream().map(Enum::name).noneMatch(s -> s.equals(language))){
            throw new InvalidCredentialsException("Invalid language");
        }
        PageRequest pageRequest = PageRequest.of(page, pageRecord);
        Page<Book> books = bookRepository.findByLanguage(Language.valueOf(language), pageRequest);

        resp.setData(books);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Page<Book>>> getAllBook(int page, int pageRecord) {
        ResponseDto<Page<Book>> resp = new ResponseDto<>();
        PageRequest pageRequest = PageRequest.of(page, pageRecord);
        Page<Book> books = bookRepository.findAllByOrderByIdDesc(pageRequest);

        resp.setData(books);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(IdRequest idRequest) {
        ResponseDto<Boolean> resp = new ResponseDto<>();
        if (Objects.isNull(idRequest.getId())) {
            throw new BlankCredentialsException("Book id cannot be blank");
        }
        if (idRequest.getId() == 0){
            throw new InvalidCredentialsException("Invalid book id");
        }
        Book book = bookRepository.findById(idRequest.getId())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid book id"));

        bookRepository.delete(book);

        resp.setData(true);
        resp.setStatusCode(ResponseCode.SUCCESSFUL.getCode());
        resp.setStatusMessage(ResponseCode.SUCCESSFUL.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NO_CONTENT);
    }
}
