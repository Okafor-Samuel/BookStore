package com.prunny.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Nationality {
    USA("USA"),
    CANADA("CANADA"),
    UK("UK"),
    FRANCE("FRANCE"),
    GERMANY("GERMANY"),
    INDIA("INDIA"),
    CHINA("CHINA"),
    JAPAN("JAPAN");

    private final String Nationality;
}
