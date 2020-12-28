package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.CartBookAdderDto;
import com.kodilla.librarybackend.domain.Volume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    @Autowired
    private VolumeMapper volumeMapper;

    public Long mapToIdFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return cartBookAdderDto.getCartId();
    }

    public Volume mapToBookFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return volumeMapper.mapToBook(cartBookAdderDto.getBookDto());
    }



}
