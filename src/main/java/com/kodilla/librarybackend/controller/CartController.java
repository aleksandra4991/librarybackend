package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.mapper.BookMapper;
import com.kodilla.librarybackend.mapper.CartMapper;
import com.kodilla.librarybackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public void createEmptyCart(){
        cartService.createEmptyCart(new Cart());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books/placed",consumes = APPLICATION_JSON_VALUE)
    public List<VolumeDto> addBookWithSpecifiedIdToSpecifiedCart (@RequestBody CartBookAdderDto cartBookAdderDto) {
        return bookMapper.mapToBookDtoList(cartService.addListOfBooksToSpecifiedCart((cartMapper.mapToIdFromCartAdderDto(cartBookAdderDto))
                , cartMapper.mapToBooksListFromCartAdderDto(cartBookAdderDto)));
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
