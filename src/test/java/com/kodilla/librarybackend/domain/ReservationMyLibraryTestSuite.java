package com.kodilla.librarybackend.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.ReaderRepository;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@DataJpaTest
@RunWith(SpringRunner.class)
public class ReservationMyLibraryTestSuite {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testGetReservations(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        bookRepository.save(specifiedBook);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook1);
        bookList.add(specifiedBook);
        Reader specifiedReader = new Reader("Aleksandra Radzikowska","566890787","aleksandraaaa@gmail.com");
        readerRepository.save(specifiedReader);
        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);
        Reservation specifiedReservation = new Reservation(specifiedCart,specifiedReader);

        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);

        Reader secondSpecifiedReader = new Reader("Julia Wrzosek","522890787","juleczkaa@gmail.com");
        readerRepository.save(secondSpecifiedReader);

        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);

        Reservation secondSpecifiedReservation = new Reservation(secondSspecifiedCart,secondSpecifiedReader);

        //When
        reservationRepository.save(specifiedReservation);
        reservationRepository.save(secondSpecifiedReservation);
        Long reservationId = specifiedReservation.getId();
        Reservation foundReservation = reservationRepository.getOne(reservationId);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);
        int numberOfReservations = bookRepository.findAll().size();

        //Then
        Assert.assertNotEquals(null, foundReservation);
        Assert.assertEquals("Julia Wrzosek",secondFoundReservation.getReader().getName());
        Assert.assertEquals(2,numberOfReservations);

        }

    @Test
    public void testCreateReservation(){

        //Given
        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);

        Reader secondSpecifiedReader = new Reader("Julia Wrzosek","522890787","juleczkaa@gmail.com");
        readerRepository.save(secondSpecifiedReader);

        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);

        Reservation secondSpecifiedReservation = new Reservation(secondSspecifiedCart,secondSpecifiedReader);

        //When
        reservationRepository.save(secondSpecifiedReservation);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);
        int numberOfReservations = bookRepository.findAll().size();

        //Then
        Assert.assertEquals(1,numberOfReservations);
        Assert.assertEquals("522890787",secondFoundReservation.getReader().getPhoneNumber());
        Assert.assertNotEquals("xxx@gmail.com",secondFoundReservation.getReader().getEmailAddress());

    }

    @Test
    public void testGetSpecifiedReservation(){

        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);

        Reader secondSpecifiedReader = new Reader("Julia Wrzosek","522890787","juleczkaa@gmail.com");
        secondSpecifiedReader.setBookList(secondBookList);
        readerRepository.save(secondSpecifiedReader);

        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);

        Reservation secondSpecifiedReservation = new Reservation(secondSspecifiedCart,secondSpecifiedReader);

        //When
        reservationRepository.save(secondSpecifiedReservation);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);
        int numberOfReservations = bookRepository.findAll().size();
        String signatureOfReservedBook =secondFoundReservation.getReader().getBookList().get(0).getSignature();

        //Then
        Assert.assertEquals(1,numberOfReservations);
        Assert.assertNotEquals("522800787",secondFoundReservation.getReader().getPhoneNumber());
        Assert.assertEquals("juleczkaa@gmail.com",secondFoundReservation.getReader().getEmailAddress());
        Assert.assertEquals("B09876",signatureOfReservedBook);

    }

    @Test
    public void testDeleteSpecifiedReservation() {

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        bookRepository.save(specifiedBook);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook1);
        bookList.add(specifiedBook);
        Reader specifiedReader = new Reader("Aleksandra Radzikowska","566890787","aleksandraaaa@gmail.com");
        readerRepository.save(specifiedReader);
        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);
        Reservation specifiedReservation = new Reservation(specifiedCart,specifiedReader);

        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);

        Reader secondSpecifiedReader = new Reader("Julia Wrzosek","522890787","juleczkaa@gmail.com");
        readerRepository.save(secondSpecifiedReader);

        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);

        Reservation secondSpecifiedReservation = new Reservation(secondSspecifiedCart,secondSpecifiedReader);

        //When
        reservationRepository.save(specifiedReservation);
        reservationRepository.save(secondSpecifiedReservation);
        Long reservationId = specifiedReservation.getId();
        Reservation foundReservation = reservationRepository.getOne(reservationId);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);
        int numberOfReservationsBeforeDeletion = reservationRepository.findAll().size();
        reservationRepository.delete(secondFoundReservation);
        int numberOfReservationsAfterDeletion = reservationRepository.findAll().size();

        //Then
        Assert.assertEquals(2,numberOfReservationsBeforeDeletion);
        Assert.assertEquals(1,numberOfReservationsAfterDeletion);

    }

    @Test
    public void testUpdateWithExpirationOfReservation(){

        //Given
        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);

        Reader secondSpecifiedReader = new Reader("Julia Wrzosek","522890787","juleczkaa@gmail.com",true);
        secondSpecifiedReader.setBookList(secondBookList);
        readerRepository.save(secondSpecifiedReader);

        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);

        Reservation secondSpecifiedReservation = new Reservation(secondSspecifiedCart,secondSpecifiedReader);
        reservationRepository.save(secondSpecifiedReservation);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);

        //When
        AtomicBoolean valueTrue = new AtomicBoolean(true);
        AtomicBoolean valueFalse = new AtomicBoolean(false);
        reservationRepository.getOne(secondReservationId).setActive(valueTrue);
        AtomicBoolean statusBeforeExpiration = secondFoundReservation.getActive();
        secondFoundReservation.setActive(valueFalse);
        AtomicBoolean statusAfterExpiration = secondFoundReservation.getActive();

        //Then
        Assert.assertTrue(statusBeforeExpiration.get()==true);
        Assert.assertFalse(statusAfterExpiration.get()==true);

    }


}

