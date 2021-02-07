package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.mapper.CartMapper;
import com.kodilla.librarybackend.mapper.VolumeMapper;
import com.kodilla.librarybackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/myLibrary")
public class CartController {

    @Lazy
    @Autowired
    private CartMapper cartMapper;

    @Lazy
    @Autowired
    private CartService cartService;

    @Lazy
    @Autowired
    private VolumeMapper volumeMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public Long createEmptyCart(List <Volume> volumeList){
        return cartService.createEmptyCart();
    }

    /*@RequestMapping(method = RequestMethod.DELETE, value = "books/deleted", consumes = APPLICATION_JSON_VALUE)
    public void removeBookWithSpecifiedIdFromSpecifiedCart (@RequestBody CartBookRemoverDto cartBookRemoverDto){
        cartService.removeBookWithSpecifiedIdFromSpecifiedCart(cartBookRemoverDto.getCartId(), cartBookRemoverDto.getBookId());
    }*/

    @RequestMapping(method = RequestMethod.POST, value = " /books/putInCart", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public List <VolumeDto> putFoundBookInTheCart(@RequestBody CartBookAdderDto cartBookAdderDto){
        return volumeMapper.mapToBookDtoList(cartService.addListOfBooksToSpecifiedCart(cartMapper.mapToIdFromCartAdderDto(cartBookAdderDto)
                , cartMapper.mapToBookFromCartAdderDto(cartBookAdderDto)));
    }

    @RequestMapping(method = RequestMethod.PUT , value = "reservation/create/cart", consumes = APPLICATION_JSON_VALUE)
    public void createReservationByCartId(@RequestBody ReservationCreationDto reservationCreationDto){
        cartService.createReservationByCartId(reservationCreationDto.getReaderId(),reservationCreationDto.getCartId());
    }



}
