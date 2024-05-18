package com.svalero.readify.service;

import com.svalero.readify.domain.Book;
import com.svalero.readify.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    //region GET
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public List<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }
    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }
    public List<Book> findByTitleAndAuthor(String title, String author){
        return bookRepository.findByTitleAndAuthor(title,author);
    }
    //endregion

    //region POST
    public void saveBook(Book book){
        bookRepository.save(book);
    }
    //endregion

    //region PUT
    public void modifyBook(Book newBook, long bookId){
        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()){
            Book existingBook = book.get();

            existingBook.setTitle(newBook.getTitle());
            existingBook.setAuthor(newBook.getAuthor());
            existingBook.setPublishedDate(newBook.getPublishedDate());
            existingBook.setISBN(newBook.getISBN());
            existingBook.setAvailable(newBook.getAvailable());
            existingBook.setPageCount(newBook.getPageCount());

            bookRepository.save(existingBook);
        }
    }
    //endregion

    //region DELETE
    public void removeBook(long bookId){
        bookRepository.deleteById(bookId);
    }
    //endregion
}
