package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.CartBookAdderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    @Autowired
    private BookMapper bookMapper;

    public Long mapToIdFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return cartBookAdderDto.getCartId();
    }

    public List<Volume> mapToBooksListFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return bookMapper.mapToBookList(cartBookAdderDto.getBookDtoList());
    }



}
