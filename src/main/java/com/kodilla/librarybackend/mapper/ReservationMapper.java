package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Component
public class ReservationMapper {

    @Lazy
    @Autowired
    private ReservationRepository reservationRepository;

    public List<ReservationDto> mapToReservationDtoList(final List<Reservation> reservations) {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDto reservationDto = new ReservationDto(true,String.valueOf(reservation.getReader()),String.valueOf(reservation.getCart().getBooks()));
            reservationDto.setReservationId(reservation.getId());
            reservationDtos.add(reservationDto);
        }
        return reservationDtos;
    }

    public ReservationDto mapToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto(true, valueOf(reservation.getReader()), valueOf(reservation.getCart().getBooks()));
        reservationDto.setReservationId(reservation.getId());
        return reservationDto;
    }

    public List<Optional<Reservation>> mapToReservationList(final List<ReservationDto> reservationDtos) {
        return reservationDtos.stream()
                .map(r -> reservationRepository.findById(r.geReservationId()))
                .collect(Collectors.toList());
    }
}

