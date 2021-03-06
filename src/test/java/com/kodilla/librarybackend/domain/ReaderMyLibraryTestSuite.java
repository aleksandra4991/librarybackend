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
public class ReaderMyLibraryTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private VolumeRepository volumeRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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

        //CleanUp
        readerRepository.deleteAllInBatch();
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

        //CleanUp
        readerRepository.deleteAllInBatch();
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

        //CleanUp
        readerRepository.deleteAllInBatch();
    }

    @Test
    public void testGetReservationsOfSpecifiedReader(){

        //Given
        Reader testReader = new Reader("Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com",true);
        readerRepository.save(testReader);
        List<Volume> reservedVolumes = new ArrayList<>();
        Genre genre = new Genre("testGenre");
        Volume volume1 = new Volume(genre.getId());
        Volume volume2 = new Volume(genre.getId());
        volumeRepository.save(volume1);
        volumeRepository.save(volume2);
        reservedVolumes.add(volume1);
        reservedVolumes.add(volume2);
        Cart cartX = new Cart(reservedVolumes);
        cartRepository.save(cartX);
        Reservation reservationOfSpecifiedReader = new Reservation(true,testReader,cartX);
        reservationRepository.save(reservationOfSpecifiedReader);
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

        //CleanUp
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
    }

    @Test
    public void testGetRentedBooksOfSpecifiedReader(){

        //Given
        Reader testReader = new Reader("Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com",true);
        readerRepository.save(testReader);
        List<Volume> rentedVolumes = new ArrayList<>();
        Genre genre = new Genre("testGenre");
        Volume volume1 = new Volume(genre.getId());
        Volume volume2 = new Volume(genre.getId());
        volumeRepository.save(volume1);
        volumeRepository.save(volume2);
        rentedVolumes.add(volume1);
        rentedVolumes.add(volume2);
        testReader.setBookList(rentedVolumes);
        readerRepository.save(testReader);
        Long readerId = testReader.getId();
        Reader specifiedReader = readerRepository.getOne(readerId);

        //When
        int numberOfRentedBooksBySpecifiedReader = specifiedReader.getBookList().size();

        //Then
        Assert.assertEquals(2,numberOfRentedBooksBySpecifiedReader);

        //CleanUP
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
    }


}
