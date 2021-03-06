package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GenreMyLibraryTestSuite {

    private static final String GENRE_NAME_THRILLER = "Thriller";
    private static final String GENRE_NAME_ROMANCE = "Romance";

    @Autowired
    private GenreRepository genreRepository;


   /* @Test
    public void testGetAllGenres(){

        //Given
        Genre genre1 = new Genre(GENRE_NAME_ROMANCE);
        Genre genre2 = new Genre(GENRE_NAME_THRILLER);
        genreRepository.save(genre1);
        genreRepository.save(genre2);

        //When
        Long firstGenreId = genre1.getId();
        Genre firstGenre = genreRepository.getOne(firstGenreId);
        Long secondGenreId = genre2.getId();
        Genre secondGenre = genreRepository.getOne(secondGenreId);
        int genreCounter = genreRepository.findAll().size();

        //Then
        Assert.assertEquals(2,genreCounter);

        //CleanUp
        genreRepository.deleteAllInBatch();
    }
*/


    @Test
    public void testGetSpecifiedGenre(){

        //Given
        Genre genre1 = new Genre(GENRE_NAME_ROMANCE);
        genreRepository.save(genre1);

        //When
        Long genreId = genre1.getId();
        Genre specifiedGenre = genreRepository.getOne(genreId);
        String genreName = specifiedGenre.getName();

        //Then
        Assert.assertEquals("Romance",genreName);

        //CleanUp
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void testUpdateSpecifiedGenre(){

        //Given
        Genre genre1 = new Genre(GENRE_NAME_ROMANCE);
        genreRepository.save(genre1);
        Long genreId = genre1.getId();
        Genre specifiedGenreToUpdate = genreRepository.getOne(genreId);

        //When
        specifiedGenreToUpdate.setName("Comedy");
        genreRepository.save(specifiedGenreToUpdate);
        specifiedGenreToUpdate = genreRepository.getOne(genreId);
        String updatedNameOfSpecifiedGenre = specifiedGenreToUpdate.getName();

        //Then
        Assert.assertNotEquals("Romance",updatedNameOfSpecifiedGenre);

        //CleanUp
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void testCreateNewGenre(){

        //Given
        Genre genre1 = new Genre(GENRE_NAME_ROMANCE);

        //When
        genreRepository.save(genre1);
        Long genreId = genre1.getId();
        Genre newGenre = genreRepository.getOne(genreId);

        //Then
        Assert.assertNotEquals(null,newGenre);

        //CleanUp
        genreRepository.deleteAllInBatch();
    }


}
