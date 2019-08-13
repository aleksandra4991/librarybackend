package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.CartDto;
import com.kodilla.librarybackend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    CartRepository cartRepository;

    public List<CartDto> mapToCartDtoList(final List<Cart> carts) {
        return carts.stream()
                .map(c->new CartDto(c.getBooks().toString()))
                .collect(Collectors.toList());
    }

    public CartDto mapToCarteDto(Cart cart) {
        CartDto cartDto = new CartDto(cart.getBooks().toString());
        return cartDto;
    }


    public List<Cart> mapToCartList(final List<CartDto> cartDtos) {
        return cartDtos.stream()
                .map(c -> cartRepository.findById(c.getCartId()).get())
                .collect(Collectors.toList());
    }


}
