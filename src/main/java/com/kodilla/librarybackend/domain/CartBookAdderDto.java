package com.kodilla.librarybackend.domain;

import java.util.List;

public class CartBookAdderDto {

    private Long cartId;

    private List<VolumeDto> volumeDtoList;

    public CartBookAdderDto() {
    }

    public CartBookAdderDto(Long cartId, List<VolumeDto> volumeDtoList) {
        this.cartId = cartId;
        this.volumeDtoList = volumeDtoList;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<VolumeDto> getBookDtoList() {
        return volumeDtoList;
    }

    public void setBookDtoList(List<VolumeDto> volumeDtoList) {
        this.volumeDtoList = volumeDtoList;
    }
}
