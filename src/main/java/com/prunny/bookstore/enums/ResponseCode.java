package com.prunny.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESSFUL("100", "Request was successful"),
    BAD_REQUEST("400", "Bad request"),
    FAILED("-99", "Request failed");

    private final String code;
    private final String message;
}
