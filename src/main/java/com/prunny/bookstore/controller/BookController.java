package com.prunny.bookstore.controller;

import com.prunny.bookstore.dto.request.BookCreationRequest;
import com.prunny.bookstore.dto.request.BookUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.model.Book;
import com.prunny.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(value = "createBook", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> createBook(@RequestBody @Valid BookCreationRequest bookCreationRequest){
            return bookService.createBook(bookCreationRequest);
    }

    @PutMapping(value = "updateBook", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> updateBook(@RequestBody @Valid BookUpdateRequest bookUpdateRequest){
            return bookService.updateBook(bookUpdateRequest);
    }

    @GetMapping(value = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Book>> getById(@RequestParam(name = "id") Long id){
            return bookService.getById(id);
    }


    @GetMapping(value = "getByTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Book>> getByTitle(@RequestParam(name = "title") String title){
            return bookService.getByTitle(title);
    }


    @GetMapping(value = "getByPublishedDate", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Page<Book>>> getByPublishedDate(@RequestParam(name = "startDate") String startDate,
                                                               @RequestParam(name = "endDate") String endDate,
                                                               @RequestParam(defaultValue = "0", name = "page") int page,
                                                               @RequestParam(defaultValue = "20", name = "record") int record){
            return bookService.getByPublishedDate(startDate, endDate, page, record);
    }

    @GetMapping(value = "getByPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Page<Book>>> getByPrice(@RequestParam(name = "startPrice") String startPrice,
                                                       @RequestParam(name = "endPrice") String endPrice,
                                                       @RequestParam(defaultValue = "0", name = "page") int page,
                                                       @RequestParam(defaultValue = "20", name = "record") int record){
            return bookService.getByPrice(startPrice, endPrice, page, record);
    }


    @GetMapping(value = "getByLanguage", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Page<Book>>> getByLanguage(@RequestParam(name = "language") String language,
                                                          @RequestParam(defaultValue = "0", name = "page") int page,
                                                          @RequestParam(defaultValue = "20", name = "record") int record){
            return bookService.getByLanguage(language, page, record);
    }


    @GetMapping(value = "getAllBook", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Page<Book>>> getAllBook(@RequestParam(defaultValue = "0", name = "page") int page,
                                                       @RequestParam(defaultValue = "20", name = "record") int record){
            return bookService.getAllBook(page, record);
    }

    @DeleteMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> delete(@RequestBody @Valid IdRequest idRequest){
            return bookService.delete(idRequest);
    }
}
