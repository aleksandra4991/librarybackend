package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.adapter.VolumeAdapter;
import com.kodilla.librarybackend.client.GoogleBooksClient;
import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.repository.BookRepository;
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
public class BookService {

    @Autowired
    private GoogleBooksClient googleBooksClient;

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VolumeAdapter volumeAdapter;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private Set<Volume> volumes;
    private static BookService bookService;

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    public Set<Volume> getBooks() {
        return new HashSet<>(volumes);
    }

    public List<Volume> fetchBooks(){
        LOGGER.info("Fetching Google Books");
        List<VolumeDto> googleBooksDto = googleBooksClient.getGoogleBooks();
        return volumeAdapter.createVolumeList(googleBooksDto) ;
    }

    public void addBook(Volume volume) {
        this.volumes.add(volume);
    }

    public List<Volume> getAllBooks() {
        LOGGER.info("Getting all books");
        return bookRepository.findAll();
    }

    /*public List<Volume> getAvaiableToRentBooks(boolean rented) {
        LOGGER.info("Getting books avaiable to rent");
        List<Volume> volumeList = bookRepository.findAll();
        return volumeList.stream()
                .filter(book -> book.isRented() == false)
                .collect(Collectors.toList());
    }*/

    /*public List<Volume> getAlreadyRentedBooks(boolean rented) {
        LOGGER.info("Getting books already rented");
        List<Volume> volumeList = bookRepository.findAll();
        return volumeList.stream()
                .filter(book -> book.isRented() == true)
                .collect(Collectors.toList());
    }*/

    public List<Volume> getBooksOfDefiniedTitleAndAuthor(String title, String authors) {
        LOGGER.info("Getting books of:"+authors);
        List<Volume> volumeList = bookRepository.findAll();
        return volumeList.stream()
                .filter(book -> book.getTitle() == title)
                .filter(book -> book.getAuthors() == authors)
                .collect(Collectors.toList());
    }

    /*public List<Book> findByTitle(String title) {
        LOGGER.info("Getting books by title,here:"+title);
        List<Book> books = bookRepository.findAll();
        return books.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
    }*/

    public Volume getBook(final Long id){
        LOGGER.info("Getting book with id:"+id.toString());
        return bookRepository.getOne(id);
    }

    @Transactional
    public Volume createBook(final Volume volume){
        LOGGER.info("Creating new book");
        return bookRepository.save(volume);
    }

   /*public void updateBook(final Long id){
        LOGGER.info("Start of updating book with id:"+id.toString());
        bookRepository.updateBookSetRentedForId(true,id);
        Book book = getBook(id);
        entityManager.refresh(book);
        LOGGER.info("Updating book with id:"+id.toString()+" finished");
    }*/

   @Transactional
    public void deleteBook(final Long id){
        LOGGER.info("Deleting book with id:"+id.toString());
        bookRepository.deleteById(id);
    }

}

