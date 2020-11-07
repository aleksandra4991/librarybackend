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
public class ReservationServiceTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private VolumeRepository volumeRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired(required = true)
    private ReservationService reservationService;



    @Test
    public void testGetReservations(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume specifiedVolume1 = new Volume(testGenre.getId());
        Volume specifiedVolume = new Volume(testGenre.getId());
        volumeRepository.save(specifiedVolume1);
        volumeRepository.save(specifiedVolume);

        List<Volume> volumeList = new ArrayList<>();
        volumeList.add(specifiedVolume1);
        volumeList.add(specifiedVolume);
        Reader specifiedReader = new Reader("Aleksandra Radzikowska",true);
        readerRepository.save(specifiedReader);
        Cart specifiedCart = new Cart();
        cartRepository.save(specifiedCart);
        Reservation specifiedReservation = new Reservation(true,specifiedReader,specifiedCart);

        Genre secondTestGenre = new Genre("Gatunek Testowy Drugi");
        Volume thirdSpecifiedVolume = new Volume(secondTestGenre.getId());
        volumeRepository.save(thirdSpecifiedVolume);
        List<Volume> secondVolumeList = new ArrayList<>();
        secondVolumeList.add(thirdSpecifiedVolume);
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
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
    }

    @Test
    public void testGetSpecifiedReservation(){

        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume specifiedVolume1 = new Volume(testGenre.getId());
        Volume specifiedVolume = new Volume(testGenre.getId());
        volumeRepository.save(specifiedVolume);
        List<Volume> volumeList = new ArrayList<>();
        volumeList.add(specifiedVolume1);
        volumeList.add(specifiedVolume);
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
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
    }

    @Test
    public void testDeleteSpecifiedReservation(){
        //Given
        Genre testGenre = new Genre("Gatunek Testowy");
        Volume specifiedVolume1 = new Volume(testGenre.getId());
        Volume specifiedVolume = new Volume(testGenre.getId());
        volumeRepository.save(specifiedVolume);
        List<Volume> volumeList = new ArrayList<>();
        volumeList.add(specifiedVolume1);
        volumeList.add(specifiedVolume);
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

        //Clean Up
        volumeRepository.deleteAllInBatch();
        genreRepository.deleteAllInBatch();
        cartRepository.deleteAllInBatch();
        readerRepository.deleteAllInBatch();
        reservationRepository.deleteAllInBatch();
    }

}
