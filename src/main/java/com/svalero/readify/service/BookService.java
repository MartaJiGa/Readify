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
        logger.info("GET /books -> service getBooks()");
        return bookRepository.findAll();
    }
    public Optional<Book> getBookById(Long id) {
        logger.info("GET /book/{bookId} -> service getBookById()");
        return bookRepository.findById(id);
    }
    public List<Book> findByTitle(String title){
        logger.info("GET /books -> service findByTitle()");
        return bookRepository.findByTitle(title);
    }
    public List<Book> findByAuthor(String author){
        logger.info("GET /books -> service findByAuthor()");
        return bookRepository.findByAuthor(author);
    }
    public List<Book> findByTitleAndAuthor(String title, String author){
        logger.info("GET /books -> service findByTitleAndAuthor()");
        return bookRepository.findByTitleAndAuthor(title,author);
    }
    //endregion

    //region POST
    public void saveBook(Book book){
        logger.info("POST /books -> service saveBook()");
        bookRepository.save(book);
    }
    //endregion

    //region PUT
    public void modifyBook(Book newBook, long bookId){

        logger.info("ini PUT /book/{bookId} -> service modifyBook() -> findById(bookId)");
        Optional<Book> book = bookRepository.findById(bookId);
        logger.info("end PUT /book/{bookId} -> service modifyBook() -> findById(bookId)");

        if(book.isPresent()){
            Book existingBook = book.get();

            existingBook.setTitle(newBook.getTitle());
            existingBook.setAuthor(newBook.getAuthor());
            existingBook.setPublishedDate(newBook.getPublishedDate());
            existingBook.setISBN(newBook.getISBN());
            existingBook.setAvailable(newBook.getAvailable());
            existingBook.setPageCount(newBook.getPageCount());

            logger.info("ini PUT /book/{bookId} -> service modifyBook() -> save(existingBook)");
            bookRepository.save(existingBook);
            logger.info("end PUT /book/{bookId} -> service modifyBook() -> save(existingBook)");
        }
    }
    //endregion

    //region DELETE
    public void removeBook(long bookId){
        logger.info("ini DELETE /book/{bookId} -> service removeBook()");
        bookRepository.deleteById(bookId);
        logger.info("end DELETE /book/{bookId} -> service removeBook()");
    }
    //endregion
}
