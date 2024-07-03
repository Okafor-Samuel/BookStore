package com.prunny.bookstore.model;

import com.prunny.bookstore.enums.Nationality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.io.Serializable;
@Entity
@Table(name = "author")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Audited(withModifiedFlag = true)
public class Author extends BaseEntity<Long> implements Serializable {

    @Column(name = "biography")
    private String biography;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality")
    private Nationality nationality;

    @Column(name = "other_name")
    private String otherName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

}
