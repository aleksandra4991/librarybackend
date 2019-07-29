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

    @RequestMapping(method = RequestMethod.POST, name = "addBookWithSpecifiedIdToSpecifiedCart",consumes = APPLICATION_JSON_VALUE)
    public List<BookDto> addBookWithSpecifiedIdToSpecifiedCart (@RequestBody CartBookAdderDto cartBookAdderDto){
        return new ArrayList<>(Arrays.asList(new BookDto(1L,"Rok 1984","George Orwell",(long)1949,true,"1L"),
                new BookDto(2L,"Tytuł","Autor", (long) 2000,false,"2L")));
    }

    @RequestMapping(method = RequestMethod.DELETE, name = "removeBookWithSpecifiedIdToSpecifiedCart", consumes = APPLICATION_JSON_VALUE)
    public void removeBookWithSpecifiedIdToSpecifiedCart (@RequestBody CartBookRemoverDto cartBookRemoverDto){

    }

    @RequestMapping(method = RequestMethod.PUT , name = "createReservationByCartId", consumes = APPLICATION_JSON_VALUE)
    public ReservationDto createReservationByCartId(@RequestParam Long cartId){
        return new ReservationDto(1L,"reader","reservedBooks");
    }



}
