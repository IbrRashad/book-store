package com.rashad.bookstore.exception;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(String message) {
        super(message);
    }
}
