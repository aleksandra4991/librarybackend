package com.kodilla.librarybackend.domain;

public class CartBookRemoverDto {

    private Long cartId;
    private Long BookId;

    public CartBookRemoverDto() {
    }

    public CartBookRemoverDto(Long cartId, Long bookId) {
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
