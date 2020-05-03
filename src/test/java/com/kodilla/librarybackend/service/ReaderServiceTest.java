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

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReaderServiceTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired(required = true)
    private ReaderService readerService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private  CartService cartService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;



    @Test
    public void testSaveReader(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true,"XyZ198@89");
        readerRepository.save(reader1);

        //When
        Reader savedReader = readerService.saveReader(reader1);

        //Then
        Assert.assertEquals("Aleksandra Radzikowska",savedReader.getName());

        //CleanUp
        readerRepository.deleteAll();
    }

    @Test
    public void testNotSaveReaderWrongEmail(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXXgmail.com",true,"XyZ198@89");
        readerRepository.save(reader1);

        //When
        Reader savedReader = readerService.saveReader(reader1);

        //Then
        Assert.assertEquals(null,savedReader);

        //CleanUp
        readerRepository.deleteAll();
    }

    @Test
    public void testNotSaveReaderWrongPaasword(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true,"XXX");
        readerRepository.save(reader1);

        //When
        Reader savedReader = readerService.saveReader(reader1);

        //Then
        Assert.assertEquals(null,savedReader);

        //CleanUp
        readerRepository.deleteAll();
    }

    @Test
    public void testLoginReader(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true,"XyZ198@89");
        readerService.saveReader(reader1);

        //When
        boolean result = readerService.loginReader("aradzikowskaXXX@gmail.com","XyZ198@89");

        //Then
        Assert.assertEquals(true,result);

        //CleanUp
        readerRepository.deleteAll();
    }

    @Test
    public void testNotLoginReaderWrongEmailAnDPassword(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@@gmail.com",true,"XyZ198@89");
        readerService.saveReader(reader1);

        //When
        boolean result = readerService.loginReader("aradzikXX@gmail.com","XXXX@89");

        //Then
        Assert.assertEquals(false,result);

        //CleanUp
        readerRepository.deleteAll();
    }

    @Test
    public void testGetAllReaders(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true);
        Reader reader2 = new Reader("Karolina Zając","793333985","karolcia@gmail.com",true);


        //When
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        List<Reader> requestedReaders = readerService.getAllReaders();

        //Then
        assertThat(reader1, sameBeanAs(requestedReaders.get(requestedReaders.size()-2)));
        assertThat(reader2, sameBeanAs(requestedReaders.get(requestedReaders.size()-1)));


        //Clean Up
        readerRepository.deleteAll();
    }

    @Test
    public void testGetReader(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true);
        readerRepository.save(reader1);

        //When
        Reader specifiedReader = readerService.getReader(reader1.getId());

        //Then
        assertThat(reader1, sameBeanAs(specifiedReader));

        //Clean Up
        readerRepository.deleteAll();

    }

    @Test
    public void testFindReaderByLoginData () {

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true,"XyZ198@89");
        readerService.saveReader(reader1);

        //When
        Reader foundReader = readerService.findReaderByLoginData("aradzikowskaXXX@gmail.com","XyZ198@89");

        //Then
        Assert.assertNotEquals(null,foundReader);
        Assert.assertNotEquals("XXXX",foundReader.getName());

        //Clean Up
        readerRepository.deleteAll();
    }

    @Test
    public void testGetReservationsOfSpecifiedReader(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@@gmail.com",true,"XyZ198@89");
        readerRepository.save(reader1);
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        bookRepository.save(specifiedBook);
        bookRepository.save(specifiedBook1);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook1);
        bookList.add(specifiedBook);
        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);
        Reservation specifiedReservation = new Reservation(true,reader1,specifiedCart);
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(specifiedReservation);
        reader1.setReservations(reservationList);
        reservationRepository.save(specifiedReservation);

        //When
        List <Reservation> reservationsOfReader = readerService.getReservationsOfSpecifiedReader(reader1.getId());

        //Then
        Assert.assertNotEquals(null,reservationsOfReader);
        Assert.assertEquals(1,reservationsOfReader.size());

        //Clean Up
        bookRepository.deleteAll();
        reservationRepository.deleteAll();


    }

   @Test
    public void testGetRentedBooksOfSpecifiedReader(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@@gmail.com",true,"XyZ198@89");
        readerRepository.save(reader1);
        Genre testGenre = new Genre("Gatunek Testowy");
        Book specifiedBook1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book specifiedBook = new Book("Tytuł2", "Autor2", (long) 1958, "B19878", testGenre);
        bookRepository.save(specifiedBook);
        bookRepository.save(specifiedBook1);
        List<Book> bookList = new ArrayList<>();
        bookList.add(specifiedBook1);
        bookList.add(specifiedBook);
        reader1.setBookList(bookList);

        //When
       List<Book> booksRentedByReader1 = readerService.getRentedBooksOfSpecifiedReader(reader1.getId());

       //Then
       Assert.assertEquals(2,booksRentedByReader1.size());

       //CleanUp
       bookRepository.deleteAll();
       readerRepository.deleteAll();


    }

    @Test
    public void testDeleteReader(){
        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true);
        readerRepository.save(reader1);
        Reader specifiedReader = readerService.getReader(reader1.getId());

        long readerCounterBeforeDeletion = readerRepository.count();

        //When
        readerService.deleteReader(specifiedReader.getId());
        long readerCounterAfterDeletion = readerRepository.count();

        //Then
        Assert.assertEquals(readerCounterBeforeDeletion - 1,readerCounterAfterDeletion );
    }
}
