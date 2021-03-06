package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GenreServiceTest {

    @Autowired(required = true)
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

   /* @Test
    public void testGetAllGenres()  {
        //Given
        Genre genre2 = new Genre("romance");
        Genre genre3 = new Genre("thiller");

        //When
        genreService.createNewGenre(genre2);
        genreService.createNewGenre(genre3);
        List<Genre> allGenres = genreService.getAllGenres();

        //Then
        Assert.assertEquals(2, allGenres.size());

        //Clean Up
        genreService.deleteGenre(genre2.getId());
        genreService.deleteGenre(genre3.getId());

    }*/

    @Test
    public void testGetGenre(){

        //Given
        Genre genre1 = new Genre("sci-fi");
        genreRepository.save(genre1);

        //When
        Genre specifiedGenre = genreService.getSpecifiedGenre(genre1.getId());

        //Then
        assertThat(genre1, sameBeanAs(specifiedGenre));

        //Clean Up
        genreRepository.deleteAllInBatch();

    }

    @Test
    public void testCreateNewGenre(){

        //Given
        Genre testGenre = new Genre();
        genreRepository.save(testGenre);

        //When
        Genre createdGenre = genreService.createNewGenre(testGenre);

        //Then
        Assert.assertNotEquals(null,createdGenre);

        //CleanUp
        genreRepository.deleteAllInBatch();

    }

    @Test
    public void testDeleteGenre(){
        //Given
        Genre genre1 = new Genre("sci-fi");
        genreRepository.save(genre1);
        Genre specifiedGenre = genreService.getSpecifiedGenre(genre1.getId());

        long genreCounterBeforeDeletion = genreRepository.count();

        //When
        genreService.deleteGenre(specifiedGenre.getId());
        long genreCounterAfterDeletion = genreRepository.count();

        //Then
        Assert.assertEquals(genreCounterBeforeDeletion - 1, genreCounterAfterDeletion);

    }
}
