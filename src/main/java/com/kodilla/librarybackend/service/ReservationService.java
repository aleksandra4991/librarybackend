package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.ReaderRepository;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    public List<Reservation> getReservations() {
        LOGGER.info("Get all reservations");
        return reservationRepository.findAll();
    }

    public Reservation getSpecifiedReservation(final Long id) {
        LOGGER.info("Getting reservation,reservationId:"+id.toString());
        return reservationRepository.getOne(id);
    }

    public Reservation createReservation(ReservationCreationDto reservationCreationDto) {
        LOGGER.info("Starting creation of reservation");
        Reader specifiedReader = readerRepository.getOne(reservationCreationDto.getReaderId());
        Cart specifiedCart = cartRepository.getOne(reservationCreationDto.getCartId());
        LOGGER.info("Finishing in a moment: creation of reservation");
        return reservationRepository.save(new Reservation(true,specifiedReader,specifiedCart));
    }

    /*public void updateWithExpirationOfReservation(ReservationDto reservationDto){
        LOGGER.info("Starting updating of expiration the reservation");
        reservationRepository.updateReservationSetRentedForId(false,reservationDto.geReservationId());
        Reservation reservation = getSpecifiedReservation(reservationDto.geReservationId());
        entityManager.refresh(reservation);
        LOGGER.info("Finished: updating of expiration the reservation");
    }*/

    @Transactional
    public void deleteSpecifiedReservation(final Long id){
        LOGGER.info("Deleting reservation,reservationId:"+id.toString());
        reservationRepository.deleteById(id);
    }
}
