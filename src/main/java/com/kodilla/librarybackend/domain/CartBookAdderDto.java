package com.kodilla.librarybackend.domain;

public class CartBookAdderDto {

    private Long cartId;

    private VolumeDto volumeDto;

    public CartBookAdderDto() {
    }

    public CartBookAdderDto(Long cartId) {
        this.cartId = cartId;
    }

    public CartBookAdderDto(Long cartId, VolumeDto volumeDto) {
        this.cartId = cartId;
        this.volumeDto = volumeDto;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public VolumeDto getBookDto() {
        return volumeDto;
    }

    public void setBookDto(VolumeDto volumeDto) {
        this.volumeDto = volumeDto;
    }
}
