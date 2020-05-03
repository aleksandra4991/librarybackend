package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.ReaderRepository;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired(required = true)
    private ReservationService reservationService;

    @Test
    public void testGetReservations(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        bookRepository.save(specifiedBook1);
        bookRepository.save(specifiedBook);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook1);
        bookList.add(specifiedBook);
        Reader specifiedReader = new Reader("Aleksandra Radzikowska",true);
        readerRepository.save(specifiedReader);
        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);
        Reservation specifiedReservation = new Reservation(true,specifiedReader,specifiedCart);

        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);
        Reader secondSpecifiedReader = new Reader("Julia Wrzosek",true);
        readerRepository.save(secondSpecifiedReader);
        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);
        Reservation secondSpecifiedReservation = new Reservation(true,secondSpecifiedReader,secondSspecifiedCart);

        //When
        reservationRepository.save(specifiedReservation);
        reservationRepository.save(secondSpecifiedReservation);
        List<Reservation> requestedReservations = reservationService.getReservations();

        //Then
        assertThat(specifiedReservation, sameBeanAs(requestedReservations.get(requestedReservations.size()-2)));
        assertThat(secondSpecifiedReservation, sameBeanAs(requestedReservations.get(requestedReservations.size()-1)));


        //Clean Up
        reservationRepository.deleteAll();
    }

    @Test
    public void testGetSpecifiedReservation(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        bookRepository.save(specifiedBook);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook1);
        bookList.add(specifiedBook);
        Reader specifiedReader = new Reader("Aleksandra Radzikowska",true);
        readerRepository.save(specifiedReader);
        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);
        Reservation specifiedReservation = new Reservation(true,specifiedReader,specifiedCart);

        //When
        reservationRepository.save(specifiedReservation);
        Reservation requestedReservation = reservationService.getSpecifiedReservation(specifiedReservation.getId());

        //Then
        assertThat(specifiedReservation, sameBeanAs(requestedReservation));

        //Clean Up
        reservationRepository.deleteAll();
    }

    @Test
    public void testDeleteSpecifiedReservation(){
        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        bookRepository.save(specifiedBook);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook1);
        bookList.add(specifiedBook);
        Reader specifiedReader = new Reader("Aleksandra Radzikowska",true);
        readerRepository.save(specifiedReader);
        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);
        Reservation specifiedReservation = new Reservation(true,specifiedReader,specifiedCart);
        reservationRepository.save(specifiedReservation);

        long reservationCounterBeforeDeletion = reservationRepository.count();

        //When
        reservationService.deleteSpecifiedReservation(specifiedReservation.getId());
        long reservationCounterAfterDeletion = reservationRepository.count();

        //Then
        Assert.assertEquals(reservationCounterBeforeDeletion - 1,reservationCounterAfterDeletion );
    }

}
