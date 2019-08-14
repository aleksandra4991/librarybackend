package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.Reservation;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.ReaderRepository;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

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
        return cartRepository.save(cart);
    }

    public List<Book> addBookWithSpecifiedIdToSpecifiedCart(Long id, List<Book> books){
        Cart cart = cartRepository.getOne(id);
        List<Book> cartBooks = cart.getBooks();
        books.stream()
                .filter(b -> bookRepository.findById(b.getId()).isPresent())
                .forEach(b -> cartBooks.add(b));

        cartRepository.save(cart);

        return cart.getBooks();
    }

    public void removeBookWithSpecifiedIdFromSpecifiedCart(Long cartId,Long bookId){

        Cart cart = cartRepository.getOne(cartId);
        cart.getBooks().remove(bookRepository.getOne(bookId));
        cartRepository.save(cart);
    }

    public Reservation createReservationByCartId(Long readerId,Long cartId){

        Reader reader = readerRepository.getOne(readerId);
        Cart cart = cartRepository.getOne(cartId);
        Reservation reservation = new Reservation(new AtomicBoolean(true),reader, cart);
        reservationRepository.save(reservation);
        cartRepository.save(cart);

        return reservation;
    }

    @Transactional
    public void deleteReservation(final Long id){
        reservationRepository.deleteById(id);
    }
}
