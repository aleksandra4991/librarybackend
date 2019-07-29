package com.kodilla.librarybackend.domain;

public class CartBookRemoverDto {

    private Long cartId;
    private Long bookId;

    public CartBookRemoverDto() {
    }

    public CartBookRemoverDto(Long cartId, Long bookId) {
        this.cartId = cartId;
        this.bookId = bookId;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getBookId() {
        return bookId;
    }
}
