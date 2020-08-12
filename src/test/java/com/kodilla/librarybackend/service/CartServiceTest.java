package com.kodilla.librarybackend.service;


import com.kodilla.librarybackend.domain.*;
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
public class CartServiceTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Test
    public void testCreateEmptyCart(){

        //Given
        Cart testCart = new Cart();
        cartRepository.save(testCart);

        //When
        Cart specifiedCart = cartService.createEmptyCart(testCart);

        //Then
        Assert.assertNotEquals(null,specifiedCart);

        //CleanUp
        cartRepository.deleteAllInBatch();
    }

    @Test
    public void testAddBookWithSpecifiedIdToSpecifiedCart(){

        //Given
        Cart testCart = new Cart();
        cartRepository.save(testCart);

        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> booksToAddToCart = new ArrayList<>();
        booksToAddToCart.add(book1);
        booksToAddToCart.add(book2);

        //When
        List<Book> booksAlreadyAddedToCart = cartService.addListOfBooksToSpecifiedCart(testCart.getId(),booksToAddToCart);

        //Then
        Assert.assertEquals(2,booksAlreadyAddedToCart.size());

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();

    }

    @Test
    public void testRemoveBookWithSpecifiedIdFromSpecifiedCart() {

        //Given
        Cart testCart = new Cart();
        cartRepository.save(testCart);

        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> booksToAddToCart = new ArrayList<>();
        booksToAddToCart.add(book1);
        booksToAddToCart.add(book2);

        List<Book> booksAlreadyAddedToCart = cartService.addListOfBooksToSpecifiedCart(testCart.getId(),booksToAddToCart);

        //When
        cartService.removeBookWithSpecifiedIdFromSpecifiedCart(testCart.getId(),book2.getId());

        //Then
        Assert.assertEquals(1,testCart.getBooks().size());

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();

    }

    @Test
    public void testCreateReservationByCartId(){

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
        ReservationCreationDto reservationCreationDto = new ReservationCreationDto(specifiedReader.getId(),specifiedCart.getId());

        //When
        Reservation createdReservation = reservationService.createReservation(reservationCreationDto);

        //Then
        Assert.assertNotEquals(null,createdReservation);
        Assert.assertEquals("Aleksandra Radzikowska",createdReservation.getReader().getName());

        //CleanUp
        bookRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
    }
}

