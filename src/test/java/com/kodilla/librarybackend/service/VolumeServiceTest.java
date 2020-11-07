package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.repository.GenreRepository;
import com.kodilla.librarybackend.repository.VolumeRepository;
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
public class VolumeServiceTest {

    @Autowired
    private VolumeRepository volumeRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private VolumeService volumeService;

    @Test
    public void testGetAllBooks(){

        //Given

        Volume volume1 = new Volume("Dawca","Lois Lowry");
        Volume volume2 = new Volume("xxx","yyy");
        Volume volume3 = new Volume("i","ii");

        //When
        volumeService.createBook(volume1);
        volumeService.createBook(volume2);
        volumeService.createBook(volume3);
        List<Volume> requestedVolumes = volumeRepository.findAll();

        //Then
        assertThat(volume1, sameBeanAs(requestedVolumes.get(requestedVolumes.size()-3)));
        assertThat(volume2, sameBeanAs(requestedVolumes.get(requestedVolumes.size()-2)));
        assertThat(volume3,sameBeanAs(requestedVolumes.get(requestedVolumes.size()-1)));

        //CleanUp
        genreRepository.deleteAllInBatch();
        volumeRepository.deleteAllInBatch();
    }


    public void testGetBooksOfDefiniedTitleAndAuthor(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());

        //When
        volumeRepository.save(volume1);
        volumeRepository.save(volume2);
        List<Volume> requestedVolumes = volumeService.getBooksOfDefiniedTitleAndAuthor("Tytuł1","Autor1");

        //Then
        Assert.assertEquals(requestedVolumes.size(),1);

        //Clean Up
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }


    /*public void testFindByTitle(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);

        //When
        volumeRepository.save(book1);
        volumeRepository.save(book2);
        List<Book> requestedBooks = volumeService.findByTitle("Tytuł1");

        //Then
        Assert.assertEquals(requestedBooks.size(),1);

        //Clean Up
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }*/


    public void testCreateBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        volumeRepository.save(volume1);

        //When
        Volume requestedVolume = volumeService.createBook(volume1);

        //Then
        Assert.assertNotEquals(null, requestedVolume);

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }


    public void testGetBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        volumeRepository.save(volume1);

        //When
        Volume specifiedVolume = volumeService.getBook(volume1.getId());

        //Then
        assertThat(volume1, sameBeanAs(specifiedVolume));

        //Clean Up
        volumeRepository.deleteAllInBatch();

    }


    public void testDeleteBook(){
        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        volumeRepository.save(volume1);
        Volume specifiedVolume = volumeService.getBook(volume1.getId());

        long bookCounterBeforeDeletion = volumeRepository.count();

        //When
        volumeService.deleteBook(specifiedVolume.getId());
        long bookCounterAfterDeletion = volumeRepository.count();

        //Then
        Assert.assertEquals(bookCounterBeforeDeletion - 1, bookCounterAfterDeletion);
    }
}


