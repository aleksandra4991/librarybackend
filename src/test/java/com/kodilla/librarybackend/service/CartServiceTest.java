package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testCreateEmptyCart(){

        //Given
        Cart testCart = new Cart();
        cartRepository.save(testCart);

        //When
        Cart specifiedCart = cartService.createEmptyCart(testCart);

        //Then
        Assert.assertNotEquals(null,specifiedCart);
    }

    @Test
    public void testAddBookWithSpecifiedIdToSpecifiedCart(){
        //Given
        Cart testCart = new Cart();
        cartRepository.save(testCart);
        Genre testGenre = new Genre("Gatunek Testowy");
        Book book1 = new Book("Tytuł1", "Autor1", (long) 1958, "B19876", testGenre);
        Book book2 = new Book("Tytuł2", "Autor2", (long) 1959, "B19877", testGenre);
        bookRepository.save(book1);
        bookRepository.save(book2);
        List<Book> booksToAddToCart = new ArrayList<>();
        booksToAddToCart.add(book1);
        booksToAddToCart.add(book2);

        //When
        Book firstBook = booksToAddToCart.get(1);
        Book secondBook = booksToAddToCart.get(2);

        //Then
        Assert.assertNotEquals("genre",firstBook.getGenre().toString());
        Assert.assertTrue(secondBook.getYear()==(long)1959);

    }

}
