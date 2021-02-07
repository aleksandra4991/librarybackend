package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Cart;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.Reservation;
import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.ReaderRepository;
import com.kodilla.librarybackend.repository.ReservationRepository;
import com.kodilla.librarybackend.repository.VolumeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CartService {

    @Lazy
    @Autowired
    private CartRepository cartRepository;

    @Lazy
    @Autowired
    private VolumeRepository volumeRepository;

    @Lazy
    @Autowired
    private ReaderRepository readerRepository;

    @Lazy
    @Autowired
    private ReservationRepository reservationRepository;

    @Lazy
    @Autowired
    private ReaderService readerService;

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

    public Long createEmptyCart(){
        LOGGER.info("Creation of empty cart");
        List <Volume> volumes = new ArrayList<>();
        Cart cart = new Cart(volumes);
        cartRepository.save(cart);
        return cart.getId();
    }

    public List<Volume> addListOfBooksToSpecifiedCart(Long cartId, List<Volume> volumes){
        LOGGER.info("Starting creation of new Cart");
        Cart cart = new Cart();
        cartRepository.save(cart);
        cartId = cart.getId();
        Cart foundCart = cartRepository.findCartById(cartId);
        List<Volume> cartVolumes = foundCart.getBooks();
        volumes.stream()
                .forEach(v -> cartVolumes.add(v));

        cartRepository.save(foundCart);
        LOGGER.info("Books added to cart");
        return foundCart.getBooks();
    }

    /*public void removeBookWithSpecifiedIdFromSpecifiedCart(Long cartId,Long bookId){
        LOGGER.info("Removing book with id:"+bookId.toString()+" from cart:"+cartId.toString());
        Cart cart = cartRepository.getOne(cartId);
        List <Volume> volumesInCart = cart.getBooks();
        Volume volumeToRemove = volumeRepository.getOne(bookId);
        volumesInCart.remove(volumeToRemove);
        cartRepository.save(cart);
        LOGGER.info("finished: removing book with id:"+bookId.toString()+" from cart:"+cartId.toString());
    }*/

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
