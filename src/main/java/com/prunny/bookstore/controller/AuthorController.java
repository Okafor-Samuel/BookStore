package com.prunny.bookstore.controller;

import com.prunny.bookstore.dto.request.AuthorCreationRequest;
import com.prunny.bookstore.dto.request.AuthorUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.model.Author;
import com.prunny.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;


    @PostMapping(value = "createAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> createAuthor(@RequestBody @Valid AuthorCreationRequest authorCreationRequest){
            return authorService.createAuthor(authorCreationRequest);
    }

    @PutMapping(value = "updateAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> updateAuthor(@RequestBody @Valid AuthorUpdateRequest authorUpdateRequest){
            return authorService.updateAuthor(authorUpdateRequest);
    }

    @GetMapping(value = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Author>> getById(@RequestParam(name = "id") Long id){
            return authorService.getById(id);
    }

    @GetMapping(value = "getAllAuthors", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Page<Author>>> getAllAuthors(@RequestParam(defaultValue = "0", name = "page") int page,
                                                            @RequestParam(defaultValue = "20", name = "record") int record){
            return authorService.getAllAuthors(page, record);
    }

    @DeleteMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> delete(@RequestBody @Valid IdRequest idRequest){
            return authorService.delete(idRequest);
    }
}
