package com.kodilla.librarybackend.domain;

public class CartBookAdderDto {

    private Long cartId;
    private Long BookId;

    public CartBookAdderDto() {
    }

    public CartBookAdderDto(Long cartId, Long bookId) {
        this.cartId = cartId;
        BookId = bookId;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getBookId() {
        return BookId;
    }
}
