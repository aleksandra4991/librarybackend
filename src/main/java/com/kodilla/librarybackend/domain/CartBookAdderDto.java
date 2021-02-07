package com.kodilla.librarybackend.domain;

import java.util.List;

public class CartBookAdderDto {

    private Long cartId;

    private List <VolumeDto> volumeDtos;

    public CartBookAdderDto() {
    }

    public CartBookAdderDto(Long cartId) {
        this.cartId = cartId;
    }

    public CartBookAdderDto(Long cartId, List <VolumeDto> volumeDtos) {
        this.cartId = cartId;
        this.volumeDtos = volumeDtos;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<VolumeDto> getVolumeDtos() {
        return volumeDtos;
    }

    public void setVolumeDtos(List<VolumeDto> volumeDtos) {
        this.volumeDtos = volumeDtos;
    }
}
