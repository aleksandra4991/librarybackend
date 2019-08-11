package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.BookDto;
import com.kodilla.librarybackend.domain.ReaderDto;
import com.kodilla.librarybackend.domain.ReservationDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class ReaderContoller {

    @RequestMapping(method = RequestMethod.POST , value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto){
        return new ReaderDto(1L,"Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com","UNBLOCKED");
    }

    @RequestMapping(method = RequestMethod.PUT , value = "blockReader")
    public ReaderDto blockReader (@RequestParam Long readerId){
        return new ReaderDto(1L,"Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com","BLOCKED");
    }

    //@RequestMapping(method = RequestMethod.PUT , value = "updateReaderPhoneNumber")
    //public ReaderDto updateReaderData (@RequestParam Long readerId){
      //  return new ReaderDto(1L,"Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com","UNBLOCKED");
    //}

    @RequestMapping(method = RequestMethod.GET , value = "getReservationsOfSpecifiedReader")
    public List<ReservationDto> getReservationsOfSpecifiedReader (@RequestParam Long readerId){
        return new ReaderDto(1L,"Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com","UNBLOCKED").getReservationDtoList();
    }

    @RequestMapping(method = RequestMethod.GET , value = "getRentedBooksOfSpecifiedReader")
    public List<BookDto> getRentedBooksOfSpecifiedReader (@RequestParam Long readerId){
        return new ReaderDto(1L,"Aleksandra Radzikowska","509345876","aradzikowska999@gmail.com","UNBLOCKED").getBookDtoList();
    }




}
