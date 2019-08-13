package com.kodilla.librarybackend.domain;

import java.util.List;

public class CartDto {

    private Long cartId;
    private String bookDtoList;

    public CartDto() {
    }

    public CartDto(String bookDtoList) {
        this.bookDtoList = bookDtoList;
    }

    public Long getCartId() {
        return cartId;
    }

    public String getBookDtoList() {
        return bookDtoList;
    }
}
