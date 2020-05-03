package com.kodilla.librarybackend.controller;

import com.google.gson.Gson;
import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.CartBookAdderDto;
import com.kodilla.librarybackend.mapper.CartMapper;
import com.kodilla.librarybackend.service.CartService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/*@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    private final static Cart cart = new Cart();
    private final static CartBookAdderDto cartDto = new CartBookAdderDto();
    private static Gson gson = new Gson();
    private final static String jsonContent = gson.toJson(cartDto);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private CartMapper cartMapper;




}
*/