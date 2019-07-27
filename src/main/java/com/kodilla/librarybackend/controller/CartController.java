package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.CartBookAdderDto;
import com.kodilla.librarybackend.domain.CartBookRemoverDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public void createEmptyCart(){

    }

    @RequestMapping(method = RequestMethod.POST, name = "addBookWithSpecifiedIdToSpecifiedCart")
    public void addBookWithSpecifiedIdToSpecifiedCart (@RequestBody CartBookAdderDto cartBookAdderDto){

    }

    @RequestMapping(method = RequestMethod.DELETE, name = "removeBookWithSpecifiedIdToSpecifiedCart")
    public void removeBookWithSpecifiedIdToSpecifiedCart (@RequestBody CartBookRemoverDto cartBookRemoverDto){

    }

    @RequestMapping(method = RequestMethod.PUT , name = "createReservationByCartId", consumes = APPLICATION_JSON_VALUE)
    public void createReservationByCartId(@RequestParam Long cartId){

    }



}
