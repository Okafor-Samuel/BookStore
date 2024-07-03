package com.prunny.bookstore.core;

public class DuplicateCredentialsException extends RuntimeException{
    public DuplicateCredentialsException(String message) {
        super(message);
    }
}
