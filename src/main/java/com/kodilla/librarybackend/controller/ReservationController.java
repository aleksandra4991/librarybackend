package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.ReservationCreationDto;
import com.kodilla.librarybackend.domain.ReservationDto;
import com.kodilla.librarybackend.exceptions.ReservationNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class ReservationController {

    @RequestMapping(method = RequestMethod.GET, value ="getReservations")
    public List <ReservationDto> getReservations(){
        return new ArrayList<>(Arrays.asList(new ReservationDto(1L,"reader1","reservedBooks1",true),new ReservationDto(2L,"reader2","reservedBooks2",true)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createReservation",consumes = APPLICATION_JSON_VALUE)
    public void createReservation(@RequestBody ReservationCreationDto reservationCreationDto){

    }

    @RequestMapping(method = RequestMethod.GET , value = "getSpecifiedReservation")
    public ReservationDto getSpecifiedReservation(@RequestParam Long reservationId)throws ReservationNotFoundException {
        return new ReservationDto(1L,"reader1","reservedBooks1",true);
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "deleteSpecifiedReservation")
    public void deleteSpecifiedReservation(@RequestParam Long reservationId) throws ReservationNotFoundException{

    }

    @RequestMapping(method = RequestMethod.PUT , value = "updateWithExpirationOfReservation")
    public ReservationDto updateWithExpirationOfReservation (@RequestBody ReservationDto reservationDto) throws ReservationNotFoundException{
        return new ReservationDto(1L,"reader1","reservedBooks1",false);
    }



}
