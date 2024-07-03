package com.prunny.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    ENGLISH("ENGLISH"),
    FRENCH("FRENCH"),
    GERMAN("GERMAN"),
    SPANISH("SPANISH"),
    ITALIAN("ITALIAN"),
    CHINESE("CHINESE"),
    JAPANESE("JAPANESE"),
    RUSSIAN("RUSSIAN"),
    PORTUGUESE("PORTUGUESE"),
    ARABIC("ARABIC"),
    HINDI("HINDI");
    private final String Language;
}
