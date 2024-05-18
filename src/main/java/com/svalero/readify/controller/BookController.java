package com.svalero.readify.controller;

import com.svalero.readify.domain.Book;
import com.svalero.readify.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    //region GET
    @GetMapping("/books")
    public List<Book> findAll(@RequestParam(defaultValue = "")String title, @RequestParam(defaultValue = "")String author){
        if(!title.isEmpty() && author.isEmpty()){
            return bookService.findByTitle(title);
        }
        else if(title.isEmpty() && !author.isEmpty()){
            return bookService.findByAuthor(author);
        }
        else if(!title.isEmpty() && !author.isEmpty()){
            return bookService.findByTitleAndAuthor(title, author);
        }
        return bookService.getBooks();
    }
    @GetMapping("/book/{bookId}")
    public Optional<Book> getBook(@PathVariable long bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        return book;
    }
    //endregion

    //region POST
    @PostMapping("/books")
    public void saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }
    //endregion

    //region PUT
    @PutMapping("/book/{bookId}")
    public void modifyBook(@RequestBody Book book, @PathVariable long bookId) {
        bookService.modifyBook(book, bookId);
    }
    //endregion

    //region DELETE
    @DeleteMapping("/book/{bookId}")
    public void removeBook(@PathVariable long bookId) {
        bookService.removeBook(bookId);
    }
    //endregion
}
