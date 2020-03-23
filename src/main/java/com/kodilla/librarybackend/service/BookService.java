package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private Set<Book> books;
    private static BookService bookService;

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    public Set<Book> getBooks() {
        return new HashSet<>(books);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<Book> getAllBooks() {
        LOGGER.info("Getting all books");
        return bookRepository.findAll();
    }

    public List<Book> getAvaiableToRentBooks(boolean rented) {
        LOGGER.info("Getting books avaiable to rent");
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(book -> book.isRented() == false)
                .collect(Collectors.toList());
    }

    public List<Book> getAlreadyRentedBooks(boolean rented) {
        LOGGER.info("Getting books already rented");
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(book -> book.isRented() == true)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksOfDefiniedAuthor(String author) {
        LOGGER.info("Getting books of:"+author);
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(book -> book.getAuthor() == author)
                .collect(Collectors.toList());
    }

    public List<Book> findByTitle(String title) {
        LOGGER.info("Getting books by title,here:"+title);
        List<Book> books = bookRepository.findAll();
        return books.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
    }

    public Book getBook(final Long id){
        LOGGER.info("Getting book with id:"+id.toString());
        return bookRepository.getOne(id);
    }

    public Book createBook(final Book book){
        LOGGER.info("Creating new book");
        return bookRepository.save(book);
    }

   /*public void updateBook(final Long id){
        LOGGER.info("Start of updating book with id:"+id.toString());
        bookRepository.updateBookSetRentedForId(true,id);
        Book book = getBook(id);
        entityManager.refresh(book);
        LOGGER.info("Updating book with id:"+id.toString()+" finished");
    }*/

    @Transactional
    public void deleteBook(final Long id){
        LOGGER.info("Deleting book with id:"+id.toString());
        bookRepository.deleteById(id);
    }

}

