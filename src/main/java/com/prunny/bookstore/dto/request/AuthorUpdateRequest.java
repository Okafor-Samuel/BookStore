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
public class AuthorUpdateRequest {
    private Long authorId;
    private String firstName;
    private String otherName;
    private String lastName;
    private String biography;
    private String email;
    private String phoneNumber;
    private String website;
    private String nationality;
}
