package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.CartBookRemoverDto;
import com.kodilla.librarybackend.domain.ReservationCreationDto;
import com.kodilla.librarybackend.mapper.CartMapper;
import com.kodilla.librarybackend.mapper.VolumeMapper;
import com.kodilla.librarybackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/myLibrary")
public class CartController {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private VolumeMapper volumeMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public void createEmptyCart(){
        cartService.createEmptyCart(new Cart());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "books/deleted", consumes = APPLICATION_JSON_VALUE)
    public void removeBookWithSpecifiedIdFromSpecifiedCart (@RequestBody CartBookRemoverDto cartBookRemoverDto){
        cartService.removeBookWithSpecifiedIdFromSpecifiedCart(cartBookRemoverDto.getCartId(), cartBookRemoverDto.getBookId());
    }

    @RequestMapping(method = RequestMethod.PUT , value = "reservation/create/cart", consumes = APPLICATION_JSON_VALUE)
    public void createReservationByCartId(@RequestBody ReservationCreationDto reservationCreationDto){
        cartService.createReservationByCartId(reservationCreationDto.getReaderId(),reservationCreationDto.getCartId());
    }



}
