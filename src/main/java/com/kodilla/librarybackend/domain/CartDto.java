package com.kodilla.librarybackend.domain;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private Long cartId;
    private List <BookDto> bookDtoList = new ArrayList<>();

    public CartDto() {
    }

    public Long getCartId() {
        return cartId;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }
}
