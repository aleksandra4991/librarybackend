package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.CartBookAdderDto;
import com.kodilla.librarybackend.domain.Volume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    @Lazy
    @Autowired
    private VolumeMapper volumeMapper;

    public Long mapToIdFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return cartBookAdderDto.getCartId();
    }

    public List <Volume> mapToBookFromCartAdderDto(CartBookAdderDto cartBookAdderDto){
        return volumeMapper.mapToBookList(cartBookAdderDto.getVolumeDtos());
    }



}
