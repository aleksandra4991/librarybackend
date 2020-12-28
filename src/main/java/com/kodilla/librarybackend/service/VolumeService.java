package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.adapter.VolumeAdapter;
import com.kodilla.librarybackend.client.GoogleBooksClient;
import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.mapper.VolumeMapper;
import com.kodilla.librarybackend.repository.CartRepository;
import com.kodilla.librarybackend.repository.VolumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VolumeService {

    @Autowired
    private GoogleBooksClient googleBooksClient;

    @Autowired
    private VolumeRepository volumeRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private VolumeMapper volumeMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VolumeAdapter volumeAdapter;

    private static final Logger LOGGER = LoggerFactory.getLogger(VolumeService.class);

    private Set<Volume> volumes;
    private static VolumeService volumeService;

    public static VolumeService getInstance() {
        if (volumeService == null) {
            volumeService = new VolumeService();
        }
        return volumeService;
    }

    public Set<Volume> getBooks() {
        return new HashSet<>(volumes);
    }



    public List <Volume> fetchSpecifiedGoogleBook(String book){
        LOGGER.info("Fetching Google Book with requested typo:"+ book);
        return volumeAdapter.createSpecificVolume(googleBooksClient.getSpecifiedGoogleBooks(book));

    }

    public void addBook(Volume volume) {
        this.volumes.add(volume);
    }

    /*public CartBookAdderDto putFoundVolumeInCart (Volume foundVolume, String title, String authors, Long cartId){
        LOGGER.info("Putting found volume in cart started");
        Cart cart = cartRepository.findCartById(cartId);
        LOGGER.info("Cart: " + cart.toString() + " selected.");
        foundVolume = fetchSpecifiedGoogleBook(volumeMapper.mapToBook(googleBooksClient.getSpecifiedGoogleBooks(title, authors)));
        LOGGER.info("Volume: " + foundVolume.getTitle() + " found");
        Volume volumePutInCart = new Volume(foundVolume.getId(),foundVolume.getTitle(),foundVolume.getAuthors());
        volumePutInCart.setCart(cart);
        LOGGER.info("Volume added to cart");
        return new CartBookAdderDto(cartId, volumeMapper.mapToBookDto((volumePutInCart)));
    }*/


    /*public VolumeDto getBookPutInCart(CartBookAdderDto cartBookAdderDto){
        LOGGER.info("Getting books put in cart started");
        if(putFoundVolumeInCart(volumeMapper.mapToBook(cartBookAdderDto.getBookDto()), volumeMapper.mapToBook(cartBookAdderDto.getBookDto()).toString() , cartBookAdderDto.getCartId()) != null){
            VolumeDto bookPutInCart = putFoundVolumeInCart(volumeMapper.mapToBook(cartBookAdderDto.getBookDto()),volumeMapper.mapToBook(cartBookAdderDto.getBookDto()).toString(), cartBookAdderDto.getCartId()).getBookDto();
            LOGGER.info(bookPutInCart + " will be put in cart");
            return bookPutInCart;
        }
        else{
            LOGGER.info("Putting book in Cart failed");

        }
        return new VolumeDto();
    }*/

    /*public List<Volume> getAvaiableToRentBooks(boolean rented) {
        LOGGER.info("Getting books avaiable to rent");
        List<Volume> volumeList = volumeRepository.findAll();
        return volumeList.stream()
                .filter(book -> book.isRented() == false)
                .collect(Collectors.toList());
    }*/

    /*public List<Volume> getAlreadyRentedBooks(boolean rented) {
        LOGGER.info("Getting books already rented");
        List<Volume> volumeList = volumeRepository.findAll();
        return volumeList.stream()
                .filter(book -> book.isRented() == true)
                .collect(Collectors.toList());
    }*/

    public List<Volume> getBooksOfDefiniedTitleAndAuthor(String title, String authors) {
        LOGGER.info("Getting books of:"+authors);
        List<Volume> volumeList = volumeRepository.findAll();
        return volumeList.stream()
                .filter(book -> book.getTitle() == title)
                .filter(book -> book.getAuthors() == authors)
                .collect(Collectors.toList());
    }

    public Volume getBook(final Long id){
        LOGGER.info("Getting book with id:"+id.toString());
        return volumeRepository.getOne(id);
    }

    @Transactional
    public Volume createBook(final Volume volume){
        LOGGER.info("Creating new book");
        return volumeRepository.save(volume);
    }

   /*public void updateBook(final Long id){
        LOGGER.info("Start of updating book with id:"+id.toString());
        volumeRepository.updateBookSetRentedForId(true,id);
        Book book = getBook(id);
        entityManager.refresh(book);
        LOGGER.info("Updating book with id:"+id.toString()+" finished");
    }*/

    @Transactional
    public void deleteBook(final Long id){
        LOGGER.info("Deleting book with id:"+id.toString());
        volumeRepository.deleteById(id);
    }
}
