package com.kodilla.librarybackend.domain;

public class ReservationCreationDto {

    private Long readerId;
    private Long cartId;

    public ReservationCreationDto(){

    }

    public ReservationCreationDto(Long readerId, Long cartId) {
        this.readerId = readerId;
        this.cartId = cartId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public Long getCartId() {
        return cartId;
    }

}
