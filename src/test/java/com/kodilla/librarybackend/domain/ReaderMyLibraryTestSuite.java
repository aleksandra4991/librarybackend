package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.ReaderRepository;
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
public class ReaderMyLibraryTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void testCreateReader(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska",true);

        //When
        readerRepository.save(reader1);
        Long readerId = reader1.getId();
        Reader newReader = readerRepository.getOne(readerId);

        //Then
        Assert.assertNotNull(newReader);

    }

    @Test
    public void testBlockReader(){

        //Given
        Reader testReader = new Reader("Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com",true);
        readerRepository.save(testReader);
        Long readerId = testReader.getId();
        Reader specifiedReaderToBlock = readerRepository.getOne(readerId);

        //When
        specifiedReaderToBlock.setStatus(false);
        readerRepository.save(specifiedReaderToBlock);
        specifiedReaderToBlock= readerRepository.getOne(readerId);
        boolean updatedIsBlockedForSpecifiedReader = specifiedReaderToBlock.isStatus();

        //Then
        Assert.assertEquals(false,updatedIsBlockedForSpecifiedReader);

    }

    @Test
    public void testUpdateReaderData(){

        //Given
        Reader testReader = new Reader("Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com",true);
        readerRepository.save(testReader);
        Long readerId = testReader.getId();
        Reader specifiedReaderToUpdateDate = readerRepository.getOne(readerId);

        //When
        specifiedReaderToUpdateDate.setStatus(false);
        specifiedReaderToUpdateDate.setPhoneNumber("798088344");
        specifiedReaderToUpdateDate.setEmailAddress("aleksandraRadzikowska@onet.pl");
        readerRepository.save(specifiedReaderToUpdateDate);
        specifiedReaderToUpdateDate = readerRepository.getOne(readerId);
        boolean updatedStatusForSpecifiedReader = specifiedReaderToUpdateDate.isStatus();
        String updatedPhoneNumberForSpecifiedReader = specifiedReaderToUpdateDate.getPhoneNumber();
        String updatedEmailAdressForSpecifiedReader = specifiedReaderToUpdateDate.getEmailAddress();

        //Then
        Assert.assertEquals(false,updatedStatusForSpecifiedReader);
        Assert.assertNotEquals("509345876",updatedPhoneNumberForSpecifiedReader);
        Assert.assertEquals("aleksandraRadzikowska@onet.pl",updatedEmailAdressForSpecifiedReader);

    }

    @Test
    public void testGetReservationsOfSpecifiedReader(){

        //Given
        Reader testReader = new Reader("Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com",true);
        List<Book> reservedBooks = new ArrayList<>();
        Genre genre = new Genre("testGenre");
        Book book1 = new Book("Rok 1984","George Orwell",(long)1949,"B098734",genre);
        Book book2 = new Book("Tytuł","George Orwell", (long) (long)1952,"C234908",genre);
        reservedBooks.add(book1);
        reservedBooks.add(book2);
        Cart cartX = new Cart(reservedBooks);
        Reservation reservationOfSpecifiedReader = new Reservation(new AtomicBoolean(true),testReader,cartX);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservationOfSpecifiedReader);
        testReader.setReservations(reservations);
        readerRepository.save(testReader);
        Long readerId = testReader.getId();
        Reader specifiedReader = readerRepository.getOne(readerId);

        //When
        int numberOfReservationsOfSpecifiedReader = specifiedReader.getReservations().size();

        //Then
        Assert.assertEquals(1,numberOfReservationsOfSpecifiedReader);

    }

    @Test
    public void testGetRentedBooksOfSpecifiedReader(){

        //Given
        Reader testReader = new Reader("Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com",true);
        List<Book> rentedBooks = new ArrayList<>();
        Genre genre = new Genre("testGenre");
        Book book1 = new Book("Rok 1984","George Orwell",(long)1949,"B098734",genre);
        Book book2 = new Book("Tytuł","George Orwell", (long) (long)1952,"C234908",genre);
        rentedBooks.add(book1);
        rentedBooks.add(book2);
        testReader.setBookList(rentedBooks);
        readerRepository.save(testReader);
        Long readerId = testReader.getId();
        Reader specifiedReader = readerRepository.getOne(readerId);

        //When
        int numberOfRentedBooksBySpecifiedReader = specifiedReader.getBookList().size();

        //Then
        Assert.assertEquals(2,numberOfRentedBooksBySpecifiedReader);

    }
}
