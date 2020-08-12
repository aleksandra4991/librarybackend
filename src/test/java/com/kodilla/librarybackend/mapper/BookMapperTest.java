package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.BookDto;
import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void mapToBookDtoList() {

        Genre genre = new Genre("Gatunek Testowy");
        genreRepository.save(genre);

        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", genre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", genre);
        List<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2));

        BookDto bookDto1 = new BookDto("Tytuł1", "Autor1", (long) 1958, "B19876", genre.getId().toString());
        BookDto bookDto2 = new BookDto("Tytuł2", "Autor2", (long) 1959, "B19877", genre.getId().toString());
        List<BookDto> bookDtos = new ArrayList<>(Arrays.asList(bookDto1, bookDto2));

        assertThat(bookDtos, sameBeanAs(bookMapper.mapToBookDtoList(bookList)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void mapToBookDto() {

        Genre genre = new Genre("Gatunek Testowy");
        genreRepository.save(genre);

        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", genre);
        BookDto bookDto1 = new BookDto("Tytuł1", "Autor1", (long) 1958, "B19876", genre.getId().toString());

        assertThat(bookDto1 ,sameBeanAs(bookMapper.mapToBookDto(book1)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void mapToBook() {

        Genre genre = new Genre("Gatunek Testowy");
        genreRepository.save(genre);

        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", genre);
        BookDto bookDto1 = new BookDto("Tytuł1", "Autor1", (long) 1958, "B19876", genre.getId().toString());

        assertThat(book1 ,sameBeanAs(bookMapper.mapToBook(bookDto1)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }
}
