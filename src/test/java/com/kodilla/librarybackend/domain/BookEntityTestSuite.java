package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class BookEntityTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void getAllBooks() {

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);
        Book book3 = new Book("Tytuł3", "Autor3", (long) 1960, "B19878", testGenre);
        int booksCounterBeforeSave = bookRepository.findAll().size();
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        //When
        int booksCounterAfterSave = bookRepository.findAll().size();

        //Then
        Assert.assertEquals(booksCounterBeforeSave + 3, booksCounterAfterSave);

        //Clean Up
        bookRepository.deleteAll();

    }

    @Test
    public void getAvaiableToRentBooks (){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876",false, testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877",false, testGenre);
        Book book3 = new Book("Tytuł3", "Autor3", (long) 1960, "B19878",false, testGenre);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        Long firstBookId = book1.getId();
        Book firstBook = bookRepository.getOne(firstBookId);
        Long secondtBookId = book2.getId();
        Book secondBook = bookRepository.getOne(secondtBookId);
        Long thirdBookId = book3.getId();
        Book thirdBook = bookRepository.getOne(thirdBookId);
        List<Book> bookList = new ArrayList<>();
        bookList.add(firstBook);
        bookList.add(secondBook);
        bookList.add(thirdBook);


        //When
        int numberOfAvaiableBooks = bookRepository.findAll().size();
        String titleOfBook1 = firstBook.getTitle();
        String authorOfBook2 = secondBook.getAuthor();
        String signatureOfBook3 = thirdBook.getSignature();

        //Then
        Assert.assertEquals(3,numberOfAvaiableBooks);
        Assert.assertEquals("Tytuł1",titleOfBook1);
        Assert.assertEquals("Autor2",authorOfBook2);
        Assert.assertNotEquals("B19876",signatureOfBook3);

        //Clean Up
        bookRepository.deleteAll();
    }

    @Test
    public void testGetAlreadyRentedBooks () {

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876",true, testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877",true, testGenre);
        Book book3 = new Book("Tytuł3", "Autor3", (long) 1960, "B19878",true, testGenre);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        //When
        int numberOfRRentedBooks = bookRepository.findAll().size();
        boolean book11IsRented = book1.isRented();
        boolean book21IsRented = book2.isRented();
        Genre genreOfbook1 = book1.getGenre();
        Genre genreOfbook3 = book3.getGenre();

        Assert.assertNotNull(numberOfRRentedBooks);
        Assert.assertTrue(book11IsRented==book21IsRented);
        Assert.assertTrue(genreOfbook1.equals(genreOfbook3));

        //CleanUp
        bookRepository.deleteAll();
    }

    @Test
    public void testGetBooksOfDefiniedAuthor(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876",true, testGenre);
        Book book2 = new Book("Tytuł2", "Autor1", (long) 1959, "B19877",false, testGenre);
        Book book3 = new Book("Tytuł3", "Autor1", (long) 1960, "B19878",true, testGenre);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        //When
        int numberOfBooksOfSpecifiedAuthor = bookRepository.findAll().size();
        String authorOfFirstBook = book1.getAuthor();
        String authorOfSecondBook = book2.getAuthor();

        //Then
        Assert.assertNotEquals(2,numberOfBooksOfSpecifiedAuthor);
        Assert.assertTrue(authorOfFirstBook.equals(authorOfSecondBook));

        //CleanUp
        bookRepository.deleteAll();

    }

    @Test
    public void testGetBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876",true, testGenre);

        bookRepository.save(book1);

        //When
        long yearOfFirstBook = book1.getYear();

        //Then
        Assert.assertNotNull(yearOfFirstBook);

        //CleanUp
        bookRepository.deleteAll();
    }

    @Test
    public void testCreateBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876",true, testGenre);

        //When
        bookRepository.save(book1);
        Long bokkId = book1.getId();
        Book newBook = bookRepository.getOne(bokkId);

        //Then
        Assert.assertNotNull(newBook);

        //CleanUp
        bookRepository.deleteAll();
    }

    @Test
    public void testUpdateBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876",true, testGenre);
        bookRepository.save(book1);
        Long book1Id = book1.getId();
        Book specifiedBookeToUpdate = bookRepository.getOne(book1Id);

        //When
        specifiedBookeToUpdate.setRented(false);
        bookRepository.save(specifiedBookeToUpdate);
        specifiedBookeToUpdate= bookRepository.getOne(book1Id);
        boolean updatedIsRentedForSpecifiedBook = specifiedBookeToUpdate.isRented();

        //Then
        Assert.assertEquals(false,updatedIsRentedForSpecifiedBook);

        //CleanUp
        bookRepository.deleteAll();
    }

    @Test
    public void testDeleteBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);
        Book book3 = new Book("Tytuł3", "Autor3", (long) 1960, "B19878", testGenre);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        //When
        long bookCounterBeforeDeletion = bookRepository.count();
        bookRepository.delete(book1);
        long bookCounterAfterDeletion = bookRepository.count();


        //Then
        Assert.assertNotEquals(bookCounterBeforeDeletion,bookCounterAfterDeletion);

        //CleanUp
        bookRepository.deleteAll();

    }

}
