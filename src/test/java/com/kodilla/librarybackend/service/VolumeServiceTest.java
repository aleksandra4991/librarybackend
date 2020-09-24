package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.GenreRepository;
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
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void testGetAllBooks(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume("Dawca","Lois Lowry",testGenre);
        Volume volume2 = new Volume("xxx","yyy",testGenre);
        Volume volume3 = new Volume("i","ii",testGenre);

        //When
        bookService.createBook(volume1);
        bookService.createBook(volume2);
        bookService.createBook(volume3);
        List<Volume> requestedVolumes = bookRepository.findAll();

        //Then
        assertThat(volume1, sameBeanAs(requestedVolumes.get(requestedVolumes.size()-3)));
        assertThat(volume2, sameBeanAs(requestedVolumes.get(requestedVolumes.size()-2)));
        assertThat(volume3,sameBeanAs(requestedVolumes.get(requestedVolumes.size()-1)));

        //CleanUp
        genreRepository.deleteAllInBatch();
        bookRepository.deleteAllInBatch();
    }


    public void testGetBooksOfDefiniedTitleAndAuthor(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());

        //When
        bookRepository.save(volume1);
        bookRepository.save(volume2);
        List<Volume> requestedVolumes = bookService.getBooksOfDefiniedTitleAndAuthor("Tytuł1","Autor1");

        //Then
        Assert.assertEquals(requestedVolumes.size(),1);

        //Clean Up
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }


    /*public void testFindByTitle(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);

        //When
        bookRepository.save(book1);
        bookRepository.save(book2);
        List<Book> requestedBooks = bookService.findByTitle("Tytuł1");

        //Then
        Assert.assertEquals(requestedBooks.size(),1);

        //Clean Up
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();

    }*/


    public void testCreateBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        bookRepository.save(volume1);

        //When
        Volume requestedVolume = bookService.createBook(volume1);

        //Then
        Assert.assertNotEquals(null, requestedVolume);

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
    }


    public void testGetBook(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        bookRepository.save(volume1);

        //When
        Volume specifiedVolume = bookService.getBook(volume1.getId());

        //Then
        assertThat(volume1, sameBeanAs(specifiedVolume));

        //Clean Up
        bookRepository.deleteAllInBatch();

    }


    public void testDeleteBook(){
        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        bookRepository.save(volume1);
        Volume specifiedVolume = bookService.getBook(volume1.getId());

        long bookCounterBeforeDeletion = bookRepository.count();

        //When
        bookService.deleteBook(specifiedVolume.getId());
        long bookCounterAfterDeletion = bookRepository.count();

        //Then
        Assert.assertEquals(bookCounterBeforeDeletion - 1, bookCounterAfterDeletion);
    }
}


