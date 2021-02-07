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
    private VolumeService volumeService;

    @Autowired
    private VolumeRepository volumeRepository;

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
        Long cartId = cartService.createEmptyCart();

        //Then
        Assert.assertNotEquals(null,cartId);

        //CleanUp
        cartRepository.deleteAllInBatch();
    }

    @Test
    public void testAddBookWithSpecifiedIdToSpecifiedCart(){

        //Given
        Cart testCart = new Cart();
        cartRepository.save(testCart);
        Long cartId = testCart.getId();
        Cart foundCart = cartRepository.getOne(cartId);

        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());
        volumeRepository.save(volume1);
        Long bookId1 = volume1.getId();
        Volume volumeFound1 = volumeRepository.getOne(bookId1);
        volumeRepository.save(volume2);
        Long bookId2 = volume2.getId();
        Volume volumeFound2 = volumeRepository.getOne(bookId2);

        List<Volume> booksToAddToCart = new ArrayList<>();
        booksToAddToCart.add(volumeFound1);
        booksToAddToCart.add(volumeFound2);

        //When
        List<Volume> booksAlreadyAddedToCart = cartService.addListOfBooksToSpecifiedCart(foundCart.getId(),booksToAddToCart);

        //Then
        Assert.assertEquals(2,booksAlreadyAddedToCart.size());

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();

    }

    /*@Test
    public void testRemoveBookWithSpecifiedIdFromSpecifiedCart() {

        //Given
        Cart testCart = new Cart();
        cartRepository.save(testCart);
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume volume1 = new Volume(testGenre.getId());
        Volume volume2 = new Volume(testGenre.getId());
        volumeRepository.save(volume1);
        volumeRepository.save(volume2);

        List<Volume> booksToAddToCart = new ArrayList<>();
        booksToAddToCart.add(volume1);
        booksToAddToCart.add(volume2);

        List<Volume> booksAlreadyAddedToCart = cartService.addListOfBooksToSpecifiedCart(testCart.getId(),booksToAddToCart);
        cartRepository.save(testCart);

        //When
        cartService.removeBookWithSpecifiedIdFromSpecifiedCart(testCart.getId(), booksAlreadyAddedToCart.get(0).getId());

        //Then
        Assert.assertEquals(1,testCart.getBooks().size());

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();

    }*/

   /* @Test
    public void testCreateReservationByCartId(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        volumeRepository.save(specifiedBook);
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
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
    }
*/
}

