package com.kodilla.librarybackend.domain;

import com.kodilla.librarybackend.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CartMyLibraryTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCreateEmptyCart(){

        //Given
        Cart emptyCart = new Cart();

        //When
        cartRepository.save(emptyCart);
        Long firstCartId = emptyCart.getId();
        Cart firstCart = cartRepository.getOne(firstCartId);
        int numberOfCarts = cartRepository.findAll().size();

        //Then
        Assert.assertEquals(1,numberOfCarts);

    }
}
