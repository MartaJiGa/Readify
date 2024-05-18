package com.svalero.readify.controller;

import com.svalero.readify.domain.Book;
import com.svalero.readify.domain.ErrorResponse;
import com.svalero.readify.exception.BookNotFoundException;
import com.svalero.readify.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    //region GET
    @GetMapping("/books")
    public List<Book> findAll(@RequestParam(defaultValue = "")String title, @RequestParam(defaultValue = "")String author){
        if(!title.isEmpty() && author.isEmpty()){
            logger.info("GET /books?title=" + title);
            return bookService.findByTitle(title);
        }
        else if(title.isEmpty() && !author.isEmpty()){
            logger.info("GET /books?author=" + author);
            return bookService.findByAuthor(author);
        }
        else if(!title.isEmpty() && !author.isEmpty()){
            logger.info("GET /books?title=" + title + "&author=" + author);
            return bookService.findByTitleAndAuthor(title, author);
        }
        logger.info("GET /books");
        return bookService.getBooks();
    }
    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable long bookId) throws BookNotFoundException {
        logger.info("ini GET /book/" + bookId);
        Book book = bookService.getBookById(bookId);
        logger.info("end GET /book/" + bookId + " ");
        return book;
    }
    //endregion

    //region POST
    @PostMapping("/books")
    public void saveBook(@RequestBody Book book) {
        logger.info("ini POST /books");
        bookService.saveBook(book);
        logger.info("end POST /books");
    }
    //endregion

    //region PUT
    @PutMapping("/book/{bookId}")
    public void modifyBook(@RequestBody Book book, @PathVariable long bookId) {
        logger.info("ini PUT /book/" + bookId);
        bookService.modifyBook(book, bookId);
        logger.info("end PUT /book/" + bookId);
    }
    //endregion

    //region DELETE
    @DeleteMapping("/book/{bookId}")
    public void removeBook(@PathVariable long bookId) {
        logger.info("ini DELETE /book/" + bookId);
        bookService.removeBook(bookId);
        logger.info("end DELETE /book/" + bookId);
    }
    //endregion

    //region EXCEPTION HANDLER
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> badRequestException(MethodArgumentNotValidException nve){
        ErrorResponse errorResponse = new ErrorResponse(400, nve.getMessage());
        logger.error(nve.getMessage(), nve);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookNotFoundException(BookNotFoundException bnfe){
        ErrorResponse errorResponse = new ErrorResponse(404, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> internalServerError(HttpServerErrorException.InternalServerError ise){
        ErrorResponse errorResponse = new ErrorResponse(500, ise.getMessage());
        logger.error(ise.getMessage(), ise);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion
}
