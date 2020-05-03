package com.kodilla.librarybackend.domain;

import java.util.List;

public class CartBookAdderDto {

    private Long cartId;

    private List<BookDto> bookDtoList;

    public CartBookAdderDto() {
    }

    public CartBookAdderDto(Long cartId, List<BookDto> bookDtoList) {
        this.cartId = cartId;
        this.bookDtoList = bookDtoList;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}
