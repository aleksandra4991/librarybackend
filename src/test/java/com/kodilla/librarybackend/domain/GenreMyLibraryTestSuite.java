package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GenreMyLibraryTestSuite {

    private static final String GENRE_NAME_THRILLER = "Thriller";
    private static final String GENRE_NAME_ROMANCE = "Romance";

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void testGetAllGenres(){

        //Given
        Genre genre1 = new Genre(GENRE_NAME_ROMANCE);
        Genre genre2 = new Genre(GENRE_NAME_THRILLER);
        genreRepository.save(genre1);
        genreRepository.save(genre2);

        //When
        int genreCounter = genreRepository.findAll().size();

        //Then
        Assert.assertEquals(2,genreCounter);
    }

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
    }

    @Test
    public void testUpdateSpecifiedGenre(){

        //Given
        Genre genre1 = new Genre(GENRE_NAME_ROMANCE);
        genreRepository.save(genre1);

        //When
        Long genreId = genre1.getId();
        Genre specifiedGenre = genreRepository.getOne(genreId);

    }
}
