package com.svalero.readify.service;

import com.svalero.readify.domain.Book;
import com.svalero.readify.exception.BookNotFoundException;
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
    public Book getBookById(Long id) throws BookNotFoundException {
        logger.info("GET /book/{bookId} -> service getBookById(Long id) -> Id: " + id);
        return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
    }
    public List<Book> findByTitle(String title){
        logger.info("GET /books -> service findByTitle(String title) -> Title: " + title);
        return bookRepository.findByTitle(title);
    }
    public List<Book> findByAuthor(String author){
        logger.info("GET /books -> service findByAuthor(String author) -> Author: " + author);
        return bookRepository.findByAuthor(author);
    }
    public List<Book> findByTitleAndAuthor(String title, String author){
        logger.info("GET /books -> service findByTitleAndAuthor(String title, String author) -> Title: " + title + ". Author: " + author);
        return bookRepository.findByTitleAndAuthor(title,author);
    }
    //endregion

    //region POST
    public boolean saveBook(Book book){
        logger.info("ini POST /books -> service saveBook(Book book)" +
                "\n\tId:" + book.getId() +
                "\n\tTitle:" + book.getTitle() +
                "\n\tAuthor:" + book.getAuthor() +
                "\n\tPublished date:" + book.getPublishedDate() +
                "\n\tISBN:" + book.getISBN() +
                "\n\tAvailable:" + book.getAvailable() +
                "\n\tPages:" + book.getPageCount());

        Book auxBook = bookRepository.save(book);
        logger.info("end POST /books -> service saveBook(Book book)");
        
        if(auxBook != null){
            return true;
        } else{
            return false;
        }
    }
    //endregion

    //region PUT
    public void modifyBook(Book newBook, long bookId) {

        logger.info("ini PUT /book/{bookId} -> service modifyBook(Book newBook, long bookId) -> findById(bookId) -> BookId: " + bookId);
        Optional<Book> book = bookRepository.findById(bookId);
        logger.info("end PUT /book/{bookId} -> service modifyBook(Book newBook, long bookId) -> findById(bookId) -> BookId: " + bookId);

        if(book.isPresent()){
            Book existingBook = book.get();

            existingBook.setTitle(newBook.getTitle());
            existingBook.setAuthor(newBook.getAuthor());
            existingBook.setPublishedDate(newBook.getPublishedDate());
            existingBook.setISBN(newBook.getISBN());
            existingBook.setAvailable(newBook.getAvailable());
            existingBook.setPageCount(newBook.getPageCount());

            logger.info("ini PUT /book/{bookId} -> service modifyBook(Book newBook, long bookId) -> save(existingBook) -> BookId: " + bookId);
            bookRepository.save(existingBook);
            logger.info("end PUT /book/{bookId} -> service modifyBook(Book newBook, long bookId) -> save(existingBook)" +
                    "\n\tMODIFIED BOOK" +
                    "\n\tBookId: " + bookId +
                    "\n\tTitle:" + existingBook.getTitle() +
                    "\n\tAuthor:" + existingBook.getAuthor() +
                    "\n\tPublished date:" + existingBook.getPublishedDate() +
                    "\n\tISBN:" + existingBook.getISBN() +
                    "\n\tAvailable:" + existingBook.getAvailable() +
                    "\n\tPages:" + existingBook.getPageCount());
        }
    }
    //endregion

    //region DELETE
    public void removeBook(long bookId) {
        logger.info("ini DELETE /book/{bookId} -> service removeBook(long bookId)");
        bookRepository.deleteById(bookId);
        logger.info("end DELETE /book/{bookId} -> service removeBook(long bookId)");
    }
    //endregion
}
