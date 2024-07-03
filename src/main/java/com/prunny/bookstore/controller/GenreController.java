package com.prunny.bookstore.controller;

import com.prunny.bookstore.dto.request.GenreCreationRequest;
import com.prunny.bookstore.dto.request.GenreUpdateRequest;
import com.prunny.bookstore.dto.request.IdRequest;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.model.Genre;
import com.prunny.bookstore.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;


    @PostMapping(value = "createGenre", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> createGenre(@RequestBody @Valid GenreCreationRequest genreCreationRequest){
        return genreService.createGenre(genreCreationRequest);
    }

    @PutMapping(value = "updateGenre", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> updateGenre(@RequestBody @Valid GenreUpdateRequest genreUpdateRequest){
        return genreService.updateGenre(genreUpdateRequest);
    }

    @GetMapping(value = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Genre>> getById(@RequestParam(name = "id") Long id){
        return genreService.getById(id);
    }

    @GetMapping(value = "getAllGenre", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Page<Genre>>> getAllGenre(@RequestParam(defaultValue = "0", name = "page") int page,
                                                         @RequestParam(defaultValue = "20", name = "record") int record){
       return genreService.getAllGenre(page, record);
    }

    @DeleteMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Boolean>> delete(@RequestBody @Valid IdRequest idRequest){
        return genreService.delete(idRequest);
    }
}
