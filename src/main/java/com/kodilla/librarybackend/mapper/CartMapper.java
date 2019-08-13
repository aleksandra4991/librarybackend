package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.CartBookAdderDto;
import com.kodilla.librarybackend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    private BookMapper bookMapper;

    public Long mapToIdFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return cartBookAdderDto.getCartId();
    }

    public List<Book> mapToBooksListFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return bookMapper.mapToBookList(cartBookAdderDto.getBookDtoList());
    }



}
