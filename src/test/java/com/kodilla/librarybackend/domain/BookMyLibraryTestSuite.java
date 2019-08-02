package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMyLibraryTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getAllBooks(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1","Autor1", (long) 1958,"B19876",testGenre);
        Book book2 = new Book("Tytuł2","Autor2", (long) 1959,"B19877",testGenre);
        Book book3 = new Book("Tytuł3","Autor3", (long) 1960,"B19878",testGenre);
        int booksCounterBeforeSave = bookRepository.findAll().size();
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        //When
        int booksCounterAfterSave = bookRepository.findAll().size();

        //Then
        Assert.assertEquals(booksCounterBeforeSave + 3,booksCounterAfterSave);

    }
}
