package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.BookDto;
import com.kodilla.librarybackend.domain.CartDto;
import com.kodilla.librarybackend.domain.ReservationCreationDto;
import com.kodilla.librarybackend.domain.ReservationDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public void createEmptyCart(@RequestBody CartDto cartDto){

    }

    @RequestMapping(method = RequestMethod.POST, name = "addBookWithSpecifiedIdToSpecifiedCart")
    public void addBookWithSpecifiedIdToSpecifiedCart (@RequestBody BookDto bookDto){
        CartDto cartDto = new CartDto();
        cartDto.getBookDtoList().add(0,bookDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, name = "removeBookWithSpecifiedIdToSpecifiedCart")
    public void removeBookWithSpecifiedIdToSpecifiedCart (@RequestParam Long bookId){
        CartDto cartDto = new CartDto();
        cartDto.getBookDtoList().remove(new BookDto());

    }

    @RequestMapping(method = RequestMethod.PUT , name = "createReservationByCartId", consumes = APPLICATION_JSON_VALUE)
    public ReservationCreationDto createReservationByCartId(@RequestParam Long cartId){
        return new ReservationCreationDto();
    }



}
