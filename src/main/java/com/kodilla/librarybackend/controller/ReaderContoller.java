package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.BookDto;
import com.kodilla.librarybackend.domain.ReaderDto;
import com.kodilla.librarybackend.domain.ReservationDto;
import com.kodilla.librarybackend.mapper.ReaderMapper;
import com.kodilla.librarybackend.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class ReaderContoller {

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private ReaderService readerService;

    @RequestMapping(method = RequestMethod.POST , value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto){
        return readerMapper.mapToReaderDto(readerService.createReader(readerMapper.mapToReader(readerDto)));

    }

    @RequestMapping(method = RequestMethod.PUT , value = "blockReader")
    public ReaderDto blockReader (@RequestParam Long readerId){
        readerService.blockReader(readerId);
        return readerMapper.mapToReaderDto(readerService.getReader(readerId));
    }

    //@RequestMapping(method = RequestMethod.PUT , value = "updateReaderPhoneNumber")
    //public ReaderDto updateReaderData (@RequestParam Long readerId){
      //  return new ReaderDto(1L,"Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com","UNBLOCKED");
    //}

    @RequestMapping(method = RequestMethod.GET , value = "getReservationsOfSpecifiedReader")
    public List<ReservationDto> getReservationsOfSpecifiedReader (@RequestParam Long readerId){
        readerService.getReservationsOfSpecifiedReader(readerId);
        return readerMapper.mapToReaderDto(readerService.getReader(readerId)).getReservationDtoList();
    }

    @RequestMapping(method = RequestMethod.GET , value = "getRentedBooksOfSpecifiedReader")
    public List<BookDto> getRentedBooksOfSpecifiedReader (@RequestParam Long readerId){
        readerService.getRentedBooksOfSpecifiedReader(readerId);
        return readerMapper.mapToReaderDto(readerService.getReader(readerId)).getBookDtoList();
    }




}
