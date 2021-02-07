package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.ReservationCreationDto;
import com.kodilla.librarybackend.domain.ReservationDto;
import com.kodilla.librarybackend.exceptions.ReservationNotFoundException;
import com.kodilla.librarybackend.mapper.ReservationMapper;
import com.kodilla.librarybackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/myLibrary")
public class ReservationController {

    @Lazy
    @Autowired
    private ReservationService reservationService;

    @Lazy
    @Autowired
    private ReservationMapper reservationMapper;

    @RequestMapping(method = RequestMethod.GET, value ="/reservations")
    public List <ReservationDto> getReservations(){
        return reservationMapper.mapToReservationDtoList(reservationService.getReservations());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reservation/create/cart",consumes = APPLICATION_JSON_VALUE)
    public void createReservation(@RequestBody ReservationCreationDto reservationCreationDto){
        reservationService.createReservation(reservationCreationDto);
    }

    @RequestMapping(method = RequestMethod.GET , value = "/reservation")
    public ReservationDto getSpecifiedReservation(@RequestParam Long reservationId)throws ReservationNotFoundException {
        return reservationMapper.mapToReservationDto(reservationService.getSpecifiedReservation(reservationId));
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/reservation")
    public void deleteSpecifiedReservation(@RequestParam Long reservationId) throws ReservationNotFoundException{
        reservationService.deleteSpecifiedReservation(reservationId);
    }

    /*@RequestMapping(method = RequestMethod.PUT , value = "updateWithExpirationOfReservation")
    public ReservationDto updateWithExpirationOfReservation (@RequestBody ReservationCreationDto reservationCreationDto) throws ReservationNotFoundException{
        return reservationMapper.mapToReservationDto(reservationService.createReservation(reservationCreationDto));
    }*/

}
