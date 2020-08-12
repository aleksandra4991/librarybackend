package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReservationMyLibraryTestSuite {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GenreRepository genreRepository;

  
   /* @Test
    public void testGetReservations(){

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
        Long reservationId = specifiedReservation.getId();
        Reservation foundReservation = reservationRepository.getOne(reservationId);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);
        int numberOfReservations = bookRepository.findAll().size();

        //Then
        Assert.assertNotEquals(null, foundReservation);
        Assert.assertEquals("Julia Wrzosek",secondFoundReservation.getReader().getName());
        Assert.assertEquals(2,numberOfReservations);

        //CleanUp

        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
    }




    /*@Test
    public void testCreateReservation(){

        //Given
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
        reservationRepository.save(secondSpecifiedReservation);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);
        int numberOfReservations = bookRepository.findAll().size();

        //Then
        Assert.assertEquals(1,numberOfReservations);

        //CleanUp

        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
    }
*/
  

   /* @Test

    public void testGetSpecifiedReservation(){

        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);
        Reader secondSpecifiedReader = new Reader("Julia Wrzosek",true);
        secondSpecifiedReader.setBookList(secondBookList);
        readerRepository.save(secondSpecifiedReader);
        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);

        Reservation secondSpecifiedReservation = new Reservation(true,secondSpecifiedReader,secondSspecifiedCart);

        //When
        reservationRepository.save(secondSpecifiedReservation);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);
        int numberOfReservations = reservationRepository.findAll().size();
        String signatureOfReservedBook =secondFoundReservation.getReader().getBookList().get(0).getSignature();

        //Then
        Assert.assertEquals(1,numberOfReservations);
        Assert.assertEquals("B09876",signatureOfReservedBook);

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
    }
        bookRepository.deleteById(thirdSpecifiedBook.getId());
        reservationRepository.deleteById(secondSpecifiedReservation.getId());
    }*/

    /*@Test
    public void testDeleteSpecifiedReservation() {

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

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
    }
*/

    @Test
    public void testUpdateWithExpirationOfReservation(){

        //Given
        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Book thirdSpecifiedBook = new Book("Tytuł3", "Autor3", (long) 1999, "B09876", secondTestGenre);
        bookRepository.save(thirdSpecifiedBook);
        List<Book> secondBookList = new ArrayList<>();
        secondBookList.add(thirdSpecifiedBook);
        Reader secondSpecifiedReader = new Reader("Julia Wrzosek",true);
        secondSpecifiedReader.setBookList(secondBookList);
        readerRepository.save(secondSpecifiedReader);
        Cart secondSspecifiedCart = new Cart();
        cartRepository.save(secondSspecifiedCart);
        Reservation secondSpecifiedReservation = new Reservation(true,secondSpecifiedReader,secondSspecifiedCart);
        reservationRepository.save(secondSpecifiedReservation);
        Long secondReservationId = secondSpecifiedReservation.getId();
        Reservation secondFoundReservation = reservationRepository.getOne(secondReservationId);

        //When
        boolean valueTrue = true;
        boolean valueFalse = false;
        reservationRepository.getOne(secondReservationId).setActive(valueTrue);
        boolean statusBeforeExpiration = secondFoundReservation.getActive();
        secondFoundReservation.setActive(valueFalse);
        boolean statusAfterExpiration = secondFoundReservation.getActive();

        //Then
        Assert.assertTrue(statusBeforeExpiration == true);
        Assert.assertFalse(statusAfterExpiration == true);

        //CleanUp
       bookRepository.deleteAllInBatch();
       genreRepository.deleteAllInBatch();
       cartRepository.deleteAllInBatch();
       reservationRepository.deleteAllInBatch();
       readerRepository.deleteAllInBatch();

    }


}

