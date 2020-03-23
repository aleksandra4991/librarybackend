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

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMyLibraryTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void testCreateEmptyCart(){

        //Given
        Cart emptyCart = new Cart();

        //When
        cartRepository.save(emptyCart);
        Long firstCartId = emptyCart.getId();
        Cart firstCart = cartRepository.getOne(firstCartId);
        int numberOfCarts = cartRepository.findAll().size();

        //Then
        Assert.assertEquals(1,numberOfCarts);

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    public void testAddBookWithSpecifiedIdToSpecifiedCart(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book specifiedBook = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        List<Book> bookList = new ArrayList<>();
        bookRepository.save(specifiedBook);
        bookList.add(specifiedBook);
        Cart specifiedCart = new Cart();
        specifiedCart.setBooks(bookList);

        cartRepository.save(specifiedCart);

        //When
        Long idOfSpecifiedCart = specifiedCart.getId();
        Cart savedSpecifiedCart = cartRepository.getOne(idOfSpecifiedCart);
        String specifiedBookName = savedSpecifiedCart.getBooks().get(0).getTitle();

        //Then
        Assert.assertEquals("Tytuł1",specifiedBookName);
        Assert.assertTrue(specifiedCart.getBooks().size()==1);

        //CleanUp
        bookRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void testRemoveBookWithSpecifiedIdFromSpecifiedCart(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book specifiedBook = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        List<Book> bookList = new ArrayList<>();
        bookRepository.save(specifiedBook);
        bookList.add(specifiedBook);
        Cart specifiedCart = new Cart();
        specifiedCart.setBooks(bookList);

        cartRepository.save(specifiedCart);
        Long idOfSpecifiedCart = specifiedCart.getId();
        Cart savedSpecifiedCart = cartRepository.getOne(idOfSpecifiedCart);

        //When
        savedSpecifiedCart.getBooks().remove(0);
        int quantityOfBooksinCartAfterRemovingBook = savedSpecifiedCart.getBooks().size();

        //Then
        Assert.assertEquals(0,quantityOfBooksinCartAfterRemovingBook);

        //CleanUp
        bookRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void testCreateReservationByCartId(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        genreRepository.save(testGenre);
        Book specifiedBook = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        bookRepository.save(specifiedBook);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook);

        Reader specifiedReader = new Reader("Aleksandra Radzikowska",true);
        readerRepository.save(specifiedReader);

        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);

        Reservation specifiedReservation = new Reservation(true,specifiedReader,specifiedCart);

        //When
        reservationRepository.save(specifiedReservation);
        Long reservationId = specifiedReservation.getId();
        Reservation foundReservation = reservationRepository.getOne(reservationId);

        //Then
        Assert.assertNotEquals(null, foundReservation);

        //CleanUp
        reservationRepository.deleteAll();
    }


}
