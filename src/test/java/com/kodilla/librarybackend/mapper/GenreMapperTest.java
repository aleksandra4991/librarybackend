package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.domain.GenreDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreMapperTest {

    @Autowired
    private GenreMapper genreMapper;

    private static final Genre genre1 = new Genre("genre1");
    private static final Genre genre2 = new Genre("genre2");

    private static final GenreDto genreDto1 = new GenreDto("genre1");
    private static final GenreDto genreDto2 = new GenreDto("genre2");

    private static final List<Genre> genreList = new ArrayList<>(Arrays.asList(genre1,genre2));
    private static final List<GenreDto> genreDtoList = new ArrayList<>(Arrays.asList(genreDto1, genreDto2));

    @Test
    public void mapToGenreDtoList() {
        assertThat(genreDtoList, sameBeanAs(genreMapper.mapToGenreDtoList(genreList)));
    }

    @Test
    public void mapToGenreDto() {
        assertThat(genreDto1 ,sameBeanAs(genreMapper.mapToGenreDto(genre1)));
    }

    @Test
    public void mapToGenre() {
        assertThat(genre2, sameBeanAs(genreMapper.mapToGenre(genreDto2)));
    }
}
