package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class ReservationMapperTest {
/*
    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void mapToReservationDtoList() {

        Reader reader1 = new Reader("Aleksandra Radzikowska", true);
        Reader reader2 = new Reader("Sylwia Radzikowska", true);

        ReaderDto readerDto1 = new ReaderDto("Aleksandra Radzikowska",true);
        ReaderDto readerDto2 = new ReaderDto("Sylwia Radzikowska",true);

        List<BookDto> bookDtos1 = new ArrayList<>();
        List<BookDto> bookDtos2 = new ArrayList<>();

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();

        Genre genre = new Genre("Test Genre");

        BookDto bookDto1 = new BookDto("Tytuł1", "Autor1", (long) 1958, "B19876", genre.getId().toString());
        BookDto bookDto2 = new BookDto("Tytuł2", "Autor2", (long) 1959, "B19877", genre.getId().toString());

        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", genre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", genre);

        bookRepository.save(book1);
        bookRepository.save(book2);

        Long firstBookId = book1.getId();
        Book firstBook = bookRepository.getOne(firstBookId);
        Long secondtBookId = book2.getId();
        Book secondBook = bookRepository.getOne(secondtBookId);

        bookDtos1.add(bookDto1);
        bookDtos2.add(bookDto2);

        books1.add(firstBook);
        books2.add(secondBook);

        Cart cart1 = new Cart(books1);
        Cart cart2 = new Cart(books2);

        CartDto cartDto1 = new CartDto(bookDtos1);
        CartDto cartDto2 = new CartDto(bookDtos2);

        Reservation reservation1 = new Reservation(new AtomicBoolean(true),reader1,cart1);
        Reservation reservation2 = new Reservation(new AtomicBoolean(true),reader2,cart2);

        ReservationDto reservationDto1 = new ReservationDto(new AtomicBoolean(true),readerDto1.toString(),cartDto1.getBookDtoList().toString());
        ReservationDto reservationDto2 = new ReservationDto(new AtomicBoolean(true),readerDto2.toString(),cartDto2.getBookDtoList().toString());

        List<Reservation> reservations = new ArrayList<>(Arrays.asList(reservation1,reservation2));
        List<ReservationDto> reservationDtos = new ArrayList<>(Arrays.asList(reservationDto1,reservationDto2));
        assertThat(reservationDtos, sameBeanAs(reservationMapper.mapToReservationDtoList(reservations)));
    }

    @Test
    public void mapToReservationDto() {

        Reader reader1 = new Reader("Aleksandra Radzikowska", true);
        ReaderDto readerDto1 = new ReaderDto("Aleksandra Radzikowska",true);

        List<BookDto> bookDtos1 = new ArrayList<>();
        List<Book> books1 = new ArrayList<>();

        Genre genre = new Genre("Test Genre");
        BookDto bookDto1 = new BookDto("Tytuł1", "Autor1", (long) 1958, "B19876", genre.getId().toString());
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", genre);

        bookRepository.save(book1);

        Long firstBookId = book1.getId();
        Book firstBook = bookRepository.getOne(firstBookId);

        bookDtos1.add(bookDto1);
        books1.add(firstBook);

        Cart cart1 = new Cart(books1);
        CartDto cartDto1 = new CartDto(bookDtos1);

        Reservation reservation1 = new Reservation(new AtomicBoolean(true),reader1,cart1);
        ReservationDto reservationDto1 = new ReservationDto(new AtomicBoolean(true),readerDto1.toString(),cartDto1.getBookDtoList().toString());

        assertThat(reservationDto1 ,sameBeanAs(reservationMapper.mapToReservationDto(reservation1)));
    }

    @Test
    public void mapToReservationList() {

        Reader reader1 = new Reader("Aleksandra Radzikowska", true);
        Reader reader2 = new Reader("Sylwia Radzikowska", true);

        ReaderDto readerDto1 = new ReaderDto("Aleksandra Radzikowska",true);
        ReaderDto readerDto2 = new ReaderDto("Sylwia Radzikowska",true);

        List<BookDto> bookDtos1 = new ArrayList<>();
        List<BookDto> bookDtos2 = new ArrayList<>();

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();

        Genre genre = new Genre("Test Genre");

        BookDto bookDto1 = new BookDto("Tytuł1", "Autor1", (long) 1958, "B19876", genre.getId().toString());
        BookDto bookDto2 = new BookDto("Tytuł2", "Autor2", (long) 1959, "B19877", genre.getId().toString());

        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", genre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", genre);

        bookRepository.save(book1);
        bookRepository.save(book2);

        Long firstBookId = book1.getId();
        Book firstBook = bookRepository.getOne(firstBookId);
        Long secondtBookId = book2.getId();
        Book secondBook = bookRepository.getOne(secondtBookId);

        bookDtos1.add(bookDto1);
        bookDtos2.add(bookDto2);

        books1.add(firstBook);
        books2.add(secondBook);

        Cart cart1 = new Cart(books1);
        Cart cart2 = new Cart(books2);

        CartDto cartDto1 = new CartDto(bookDtos1);
        CartDto cartDto2 = new CartDto(bookDtos2);

        Reservation reservation1 = new Reservation(new AtomicBoolean(true),reader1,cart1);
        Reservation reservation2 = new Reservation(new AtomicBoolean(true),reader2,cart2);

        ReservationDto reservationDto1 = new ReservationDto(new AtomicBoolean(true),readerDto1.toString(),cartDto1.getBookDtoList().toString());
        ReservationDto reservationDto2 = new ReservationDto(new AtomicBoolean(true),readerDto2.toString(),cartDto2.getBookDtoList().toString());

        List<Reservation> reservations = new ArrayList<>(Arrays.asList(reservation1,reservation2));
        List<ReservationDto> reservationDtos = new ArrayList<>(Arrays.asList(reservationDto1,reservationDto2));
        assertThat(reservationDtos, sameBeanAs(reservationMapper.mapToReservationDtoList(reservations)));

        assertThat(reservations, sameBeanAs(reservationMapper.mapToReservationList(reservationDtos)));
    }
*/
//}

