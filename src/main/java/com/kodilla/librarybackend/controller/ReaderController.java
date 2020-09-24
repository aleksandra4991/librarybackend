package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.domain.ReaderDto;
import com.kodilla.librarybackend.domain.ReservationDto;
import com.kodilla.librarybackend.mapper.ReaderMapper;
import com.kodilla.librarybackend.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/myLibrary")
public class ReaderController {

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private ReaderService readerService;

    @RequestMapping(method = RequestMethod.POST , value = "/reader", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto){
        return readerMapper.mapToReaderDto(readerService.saveReader(readerMapper.mapToReader(readerDto)));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/reader/login")
    public Boolean login(@RequestHeader("emailAddress")String emailAddress,@RequestHeader("password")String password ) {
        return (readerService.loginReader(emailAddress,password));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reader/emailAddress/password")
    public ReaderDto getReaderByEmailAddressAndPassword(@RequestHeader("emailAddress")String emailAddress,@RequestHeader("password")String password ) {
        return readerMapper.mapToReaderDto(readerService.findReaderByLoginData(emailAddress,password)); }

    @RequestMapping(method = RequestMethod.GET, value = "/reader/{uuid}")
    public ReaderDto getReaderByUUID(@PathVariable String uuid) {
        return readerMapper.mapToReaderDto(readerService.findReaderByUuid(uuid)); }


    /*@RequestMapping(method = RequestMethod.PUT , value = "blockReader")
    public ReaderDto blockReader (@RequestParam Long readerId){
        readerService.blockReader(readerId);
        return readerMapper.mapToReaderDto(readerService.getReader(readerId));
    }*/

    //@RequestMapping(method = RequestMethod.PUT , value = "updateReaderPhoneNumber")
    //public ReaderDto updateReaderData (@RequestParam Long readerId){
    //  return new ReaderDto(1L,"Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com","UNBLOCKED");
    //}

    @RequestMapping(method = RequestMethod.GET , value = "/reservation/reserved")
    public List<ReservationDto> getReservationsOfSpecifiedReader (@RequestParam Long readerId){
        readerService.getReservationsOfSpecifiedReader(readerId);
        return readerMapper.mapToReaderDto(readerService.getReader(readerId)).getReservationDtoList();
    }

    @RequestMapping(method = RequestMethod.GET , value = "/books/rented")
    public List<VolumeDto> getRentedBooksOfSpecifiedReader (@RequestParam Long readerId){
        readerService.getRentedBooksOfSpecifiedReader(readerId);
        return readerMapper.mapToReaderDto(readerService.getReader(readerId)).getBookDtoList();
    }



}
