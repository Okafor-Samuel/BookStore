package com.prunny.bookstore.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreationRequest {

    private Long authorId;
    private Long genreId;
    private String title;
    private String description;
    private String language;
    private String publishedDate;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private String price;
}
