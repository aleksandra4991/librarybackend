package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.ReaderRepository;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getSpecifiedReservation(final Long id) {
        return reservationRepository.getOne(id);
    }

    public Reservation createReservation(ReservationCreationDto reservationCreationDto) {
        Reader specifiedReader = readerRepository.getOne(reservationCreationDto.getReaderId());
        Cart specifiedCart = cartRepository.getOne(reservationCreationDto.getCartId());
        return reservationRepository.save(new Reservation(specifiedReader,specifiedCart));
    }

    public void updateWithExpirationOfReservation(ReservationDto reservationDto){
        reservationRepository.updateReservationSetRentedForId(new AtomicBoolean(false),reservationDto.geReservationId());
        Reservation reservation = getSpecifiedReservation(reservationDto.geReservationId());
        entityManager.refresh(reservation);
    }

    @Transactional
    public void deleteSpecifiedReservation(final Long id){
        reservationRepository.deleteById(id);
    }
}
