package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VolumeEntityTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void getAllBooks() {

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());
        Volume volume3 = new Volume(testGenre.getId());
        int booksCounterBeforeSave = bookRepository.findAll().size();
        bookRepository.save(volume1);
        bookRepository.save(volume2);
        bookRepository.save(volume3);

        //When
        int booksCounterAfterSave = bookRepository.findAll().size();

        //Then
        Assert.assertEquals(booksCounterBeforeSave + 3, booksCounterAfterSave);

        //Clean Up
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();


    }

  @Test
    public void getAvaiableToRentBooks (){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());
        Volume volume3 = new Volume(testGenre.getId());

        bookRepository.save(volume1);
        bookRepository.save(volume2);
        bookRepository.save(volume3);
        Long firstBookId = volume1.getId();
        Volume firstVolume = bookRepository.getOne(firstBookId);
        Long secondtBookId = volume2.getId();
        Volume secondVolume = bookRepository.getOne(secondtBookId);
        Long thirdBookId = volume3.getId();
        Volume thirdVolume = bookRepository.getOne(thirdBookId);
        List<Volume> volumeList = new ArrayList<>();
        volumeList.add(firstVolume);
        volumeList.add(secondVolume);
        volumeList.add(thirdVolume);


        //When
        int numberOfAvaiableBooks = bookRepository.findAll().size();
        String titleOfBook1 = firstVolume.getTitle();
        String authorOfBook2 = secondVolume.getAuthors();


        //Then
        Assert.assertEquals(2,numberOfAvaiableBooks);
        Assert.assertEquals("Tytuł1",titleOfBook1);
        Assert.assertEquals("Autor2",authorOfBook2);


        //Clean Up

        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }





    @Test
    public void testGetBooksOfDefiniedAuthor(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());
        Volume volume3 = new Volume(testGenre.getId());

        bookRepository.save(volume1);
        bookRepository.save(volume2);
        bookRepository.save(volume3);

        //When
        int numberOfBooksOfSpecifiedAuthor = bookRepository.findAll().size();
        String authorOfFirstBook = volume1.getAuthors();
        String authorOfSecondBook = volume2.getAuthors();

        //Then
        Assert.assertNotEquals(2,numberOfBooksOfSpecifiedAuthor);
        Assert.assertTrue(authorOfFirstBook.equals(authorOfSecondBook));

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }

    @Test
    public void testGetBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());

        bookRepository.save(volume1);

        //When
        String titleOfFirstBook = volume1.getTitle();

        //Then
        Assert.assertNotNull(titleOfFirstBook);

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void testCreateBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());

        //When
        bookRepository.save(volume1);
        Long bokkId = volume1.getId();
        Volume newVolume = bookRepository.getOne(bokkId);

        //Then
        Assert.assertNotNull(newVolume);

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void testDeleteBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());
        Volume volume3 = new Volume(testGenre.getId());
        bookRepository.save(volume1);
        bookRepository.save(volume2);
        bookRepository.save(volume3);

        //When
        long bookCounterBeforeDeletion = bookRepository.count();
        bookRepository.delete(volume1);
        long bookCounterAfterDeletion = bookRepository.count();


        //Then
        Assert.assertNotEquals(bookCounterBeforeDeletion,bookCounterAfterDeletion);

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }

}
