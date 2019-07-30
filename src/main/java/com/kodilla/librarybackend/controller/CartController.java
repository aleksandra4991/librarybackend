package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public void createEmptyCart(@RequestBody CartDto cartDto){

    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookWithSpecifiedIdToSpecifiedCart",consumes = APPLICATION_JSON_VALUE)
    public CartDto addBookWithSpecifiedIdToSpecifiedCart (@RequestParam CartBookAdderDto cartBookAdderDto) {
        return new CartDto(1L, new ArrayList<>(Arrays.asList(new BookDto(1L, "Rok 1984", "George Orwell", (long) 1949, false, "1L"),
                new BookDto(2L, "Tytuł", "Autor", (long) 2000, false, "2L"))));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeBookWithSpecifiedIdToSpecifiedCart", consumes = APPLICATION_JSON_VALUE)
    public void removeBookWithSpecifiedIdToSpecifiedCart (@RequestParam CartBookRemoverDto cartBookRemoverDto){
        CartDto cartDto = new CartDto();
        cartDto.getBookDtoList().remove(new BookDto());
    }

    @RequestMapping(method = RequestMethod.PUT , value = "createReservationByCartId", consumes = APPLICATION_JSON_VALUE)
    public ReservationCreationDto createReservationByCartId(@RequestParam Long cartId){
        return new ReservationCreationDto(1L,1l);
    }



}
