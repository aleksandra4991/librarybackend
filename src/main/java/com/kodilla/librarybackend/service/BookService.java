package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.repository.BookRepository;
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
        return bookRepository.findAll();
    }

    public List<Book> getAvaiableToRentBooks(boolean rented) {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(book -> book.isRented() == false)
                .collect(Collectors.toList());
    }

    public List<Book> getAlreadyRentedBooks(boolean rented) {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(book -> book.isRented() == true)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksOfDefiniedAuthor(String author) {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(book -> book.getAuthor() == author)
                .collect(Collectors.toList());
    }

    public Set<Book> findByTitle(String title) {
        return books.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toSet());
    }

    public Book getBook(final Long id){
        return bookRepository.getOne(id);
    }

    public Book createBook(final Book book){
        return bookRepository.save(book);
    }

    public void updateBook(final Long id){
        bookRepository.updateBookSetRentedForId(true,id);
        Book book = getBook(id);
        entityManager.refresh(book);
    }

    @Transactional
    public void deleteBook(final Long id){
        bookRepository.deleteById(id);
    }


}

