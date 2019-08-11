package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired(required = true)
    private BookService bookService;

    @Test
    public void testGetAllBooks(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);
        Book book3 = new Book("Tytuł3", "Autor3", (long) 1960, "B19878", testGenre);

        //When
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        List<Book> requestedBooks = bookService.getAllBooks();

        //Then
        assertThat(book1, sameBeanAs(requestedBooks.get(requestedBooks.size()-3)));
        assertThat(book2, sameBeanAs(requestedBooks.get(requestedBooks.size()-2)));
        assertThat(book3,sameBeanAs(requestedBooks.get(requestedBooks.size()-1)));

        //Clean Up
        bookService.deleteBook(book1.getId());
        bookService.deleteBook(book2.getId());
        bookService.deleteBook(book3.getId());
    }

    @Test
    public void testGetBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        bookRepository.save(book1);

        //When
        Book specifiedBook = bookService.getBook(book1.getId());

        //Then
        assertThat(book1, sameBeanAs(specifiedBook));

        //Clean Up
        bookService.deleteBook(specifiedBook.getId());

    }

    @Test
    public void testDeleteBook(){
        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        bookRepository.save(book1);
        Book specifiedBook = bookService.getBook(book1.getId());

        long bookCounterBeforeDeletion = bookRepository.count();

        //When
        bookService.deleteBook(specifiedBook.getId());
        long bookCounterAfterDeletion = bookRepository.count();

        //Then
        Assert.assertEquals(bookCounterBeforeDeletion - 1, bookCounterAfterDeletion);
    }
}

