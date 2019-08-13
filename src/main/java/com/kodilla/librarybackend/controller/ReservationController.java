package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.ReservationCreationDto;
import com.kodilla.librarybackend.domain.ReservationDto;
import com.kodilla.librarybackend.exceptions.ReservationNotFoundException;
import com.kodilla.librarybackend.mapper.ReservationMapper;
import com.kodilla.librarybackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    @RequestMapping(method = RequestMethod.GET, value ="getReservations")
    public List <ReservationDto> getReservations(){
        return reservationMapper.mapToReservationDtoList(reservationService.getReservations());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createReservation",consumes = APPLICATION_JSON_VALUE)
    public void createReservation(@RequestBody ReservationCreationDto reservationCreationDto){
        reservationService.createReservation(reservationCreationDto);
    }

    @RequestMapping(method = RequestMethod.GET , value = "getSpecifiedReservation")
    public ReservationDto getSpecifiedReservation(@RequestParam Long reservationId)throws ReservationNotFoundException {
        return reservationMapper.mapToReservationDto(reservationService.getSpecifiedReservation(reservationId));
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "deleteSpecifiedReservation")
    public void deleteSpecifiedReservation(@RequestParam Long reservationId) throws ReservationNotFoundException{
        reservationService.deleteSpecifiedReservation(reservationId);
    }

    @RequestMapping(method = RequestMethod.PUT , value = "updateWithExpirationOfReservation")
    public ReservationDto updateWithExpirationOfReservation (@RequestBody ReservationCreationDto reservationCreationDto) throws ReservationNotFoundException{
        return reservationMapper.mapToReservationDto(reservationService.createReservation(reservationCreationDto));
    }

}
