package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.GenreRepository;
import com.kodilla.librarybackend.repository.VolumeRepository;
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
    private VolumeRepository volumeRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void getAllBooks() {

        //Given
        Volume volume1 = new Volume("Title1","Author1","01012020","xxxx");
        Volume volume2 = new Volume("Title2","Author2", "16092010","mmm");
        Volume volume3 = new Volume("Title3","Author3", "05061980","tytyty");
        int booksCounterBeforeSave = volumeRepository.findAll().size();
        volumeRepository.save(volume1);
        volumeRepository.save(volume2);
        volumeRepository.save(volume3);

        //When
        int booksCounterAfterSave = volumeRepository.findAll().size();

        //Then
        Assert.assertEquals(booksCounterBeforeSave + 3, booksCounterAfterSave);

        //Clean Up
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();


    }

  @Test
    public void getAvaiableToRentBooks (){

        //Given
        Volume volume1 = new Volume("Title1","Author1","01012020","xxxx");
        Volume volume2 = new Volume("Title2","Author2", "16092010","mmm");
        Volume volume3 = new Volume("Title3","Author3", "05061980","tytyty");

        volumeRepository.save(volume1);
        volumeRepository.save(volume2);
        volumeRepository.save(volume3);
        Long firstBookId = volume1.getId();
        Volume firstVolume = volumeRepository.getOne(firstBookId);
        Long secondtBookId = volume2.getId();
        Volume secondVolume = volumeRepository.getOne(secondtBookId);
        Long thirdBookId = volume3.getId();
        Volume thirdVolume = volumeRepository.getOne(thirdBookId);
        List<Volume> volumeList = new ArrayList<>();
        volumeList.add(firstVolume);
        volumeList.add(secondVolume);
        volumeList.add(thirdVolume);


        //When
        int numberOfAvaiableBooks = volumeRepository.findAll().size();
        String titleOfBook1 = firstVolume.getTitle();
        String authorOfBook2 = secondVolume.getAuthors();


        //Then
        Assert.assertEquals("Title1",titleOfBook1);
        Assert.assertEquals("Author2",authorOfBook2);


        //Clean Up

        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }





    @Test
    public void testGetBooksOfDefiniedAuthor(){

        //Given
        Volume volume1 = new Volume("Title1","Author1","01012020","xxxx");
        Volume volume2 = new Volume("Title2","Author2", "16092010","mmm");
        Volume volume3 = new Volume("Title3","Author3", "05061980","tytyty");

        volumeRepository.save(volume1);
        volumeRepository.save(volume2);
        volumeRepository.save(volume3);

        //When
        int numberOfBooksOfSpecifiedAuthor = volumeRepository.findAll().size();
        String authorOfFirstBook = volume1.getAuthors();
        String authorOfSecondBook = volume2.getAuthors();

        //Then
        Assert.assertFalse(authorOfFirstBook.equals(authorOfSecondBook));

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }

    @Test
    public void testGetBook(){

        //Given

        Volume volume1 = new Volume("Title1","Author1","01012020","xxxx");
        volumeRepository.save(volume1);

        //When
        String titleOfFirstBook = volume1.getTitle();

        //Then
        Assert.assertNotNull(titleOfFirstBook);

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void testCreateBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());

        //When
        volumeRepository.save(volume1);
        Long bokkId = volume1.getId();
        Volume newVolume = volumeRepository.getOne(bokkId);

        //Then
        Assert.assertNotNull(newVolume);

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void testDeleteBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());
        Volume volume3 = new Volume(testGenre.getId());
        volumeRepository.save(volume1);
        volumeRepository.save(volume2);
        volumeRepository.save(volume3);

        //When
        long bookCounterBeforeDeletion = volumeRepository.count();
        volumeRepository.delete(volume1);
        long bookCounterAfterDeletion = volumeRepository.count();


        //Then
        Assert.assertNotEquals(bookCounterBeforeDeletion,bookCounterAfterDeletion);

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }

}
