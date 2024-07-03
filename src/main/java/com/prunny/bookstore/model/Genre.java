package com.prunny.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "genre")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited(withModifiedFlag = true)
public class Genre extends BaseEntity<Long> implements Serializable {

    @Column(name = "genre_name")
    private String genreName;

    @Lob
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
