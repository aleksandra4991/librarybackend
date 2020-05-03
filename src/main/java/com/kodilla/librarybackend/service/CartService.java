package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.Reservation;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.ReaderRepository;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private Set<Cart> carts;
    private static CartService cartService;

    public static CartService getInstance() {
        if (cartService == null) {
            cartService = new CartService();
        }
        return cartService;
    }

    public Set<Cart> getCarts() {
        return new HashSet<>(carts);
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

    public Cart createEmptyCart(final Cart cart){
        LOGGER.info("Creation of empty cart");
        return cartRepository.save(cart);
    }

    public List<Book> addListOfBooksToSpecifiedCart(Long id, List<Book> books){
        LOGGER.info("Starting adding list of books to cart");
        Cart cart = cartRepository.getOne(id);
        List<Book> cartBooks = cart.getBooks();
        books.stream()
                .filter(b -> bookRepository.findById(b.getId()).isPresent())
                .forEach(b -> cartBooks.add(b));

        cartRepository.save(cart);
        LOGGER.info("Books added to cart");
        return cart.getBooks();
    }

    public void removeBookWithSpecifiedIdFromSpecifiedCart(Long cartId,Long bookId){
        LOGGER.info("Removing book with id:"+bookId.toString()+" from cart:"+cartId.toString());
        Cart cart = cartRepository.getOne(cartId);
        cart.getBooks().remove(bookRepository.getOne(bookId));
        cartRepository.save(cart);
        LOGGER.info("finished: removing book with id:"+bookId.toString()+" from cart:"+cartId.toString());
    }

    public Reservation createReservationByCartId(Long readerId,Long cartId){
        LOGGER.info("Reservation creation started,readerId:"+readerId.toString()+" ,cartId:"+cartId.toString());
        Reader reader = readerRepository.getOne(readerId);
        Cart cart = cartRepository.getOne(cartId);
        Reservation reservation = new Reservation(true,reader, cart);
        reservationRepository.save(reservation);
        cartRepository.save(cart);
        LOGGER.info("finished :reservation creation started,readerId:"+readerId.toString()+" ,cartId:"+cartId.toString());
        return reservation;
    }

    @Transactional
    public void deleteCart(final Long id){
        LOGGER.info("Deleting cart with id:"+id.toString());
        cartRepository.deleteById(id);
    }

}
