package com.svalero.readify.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(long bookId){
        super("Book not found with ID: " + bookId);
    }
}
