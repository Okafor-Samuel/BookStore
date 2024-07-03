package com.prunny.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prunny.bookstore.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited(withModifiedFlag = true)
public class Book extends BaseEntity<Long> implements Serializable {
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

    @JsonIgnore
    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name ="book_cover" )
    private String bookCover;

    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genre_fk", referencedColumnName = "id")
    private Genre genre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_fk", referencedColumnName = "id")
    private Author author;

}
