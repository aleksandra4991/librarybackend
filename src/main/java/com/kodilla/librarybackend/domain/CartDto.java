package com.kodilla.librarybackend.domain;

import java.util.List;

public class CartDto {

    private Long cartId;
    private List<BookDto> bookDtoList;

    public CartDto() {
    }

    public CartDto(Long cartId, List<BookDto> bookDtoList) {
        this.cartId = cartId;
        this.bookDtoList = bookDtoList;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }
}
