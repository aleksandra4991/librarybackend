package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.Reservation;
import com.kodilla.librarybackend.repository.ReaderRepository;
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

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderService.class);

    private Set<Reader> readers;
    private static ReaderService readerService;

    public static ReaderService getInstance() {
        if (readerService == null) {
            readerService = new ReaderService();
        }
        return readerService;
    }

    public Set<Reader> getReaders() {
        return new HashSet<>(readers);
    }

    public void addReader(Reader reader) {
        this.readers.add(reader);
    }

    public List<Reader> getAllReaders() {
        LOGGER.info("Getting all readers");
        return readerRepository.findAll();
    }

    public Reader getReader(final Long id) {
        LOGGER.info("Getting reader with id:"+id.toString());
        return readerRepository.getOne(id);
    }

    public Reader createReader(final Reader reader) {
        LOGGER.info("Creating new reader");
        return readerRepository.save(reader);
    }

    public void blockReader(final Long id) {
        LOGGER.info("Blocking reader started,readerId:"+id.toString());
        readerRepository.updateReaderSetStatusForId(false, id);
        Reader reader = getReader(id);
        entityManager.refresh(reader);
        LOGGER.info("Finished: locking reader,readerId:"+id.toString());
    }

    public List<Reservation> getReservationsOfSpecifiedReader(final Long id){
        LOGGER.info("Getting reservation of reader,readerId:"+ id.toString());
        List<Reservation> reservationsOfSpecifiedReader = readerRepository.findById(id).get().getReservations();
        return reservationsOfSpecifiedReader;
    }

    public List<Book> getRentedBooksOfSpecifiedReader(final Long id){
        LOGGER.info("Getting rented books of reader,readerId:"+ id.toString());
        List<Book> booksRentedBySpecifiedReader = readerRepository.findById(id).get().getBookList();
        return booksRentedBySpecifiedReader;
    }

    @Transactional
    public void deleteReader(final Long id){
        LOGGER.info("Deleting reader,readerId:"+id.toString());
        readerRepository.deleteById(id);
    }
}
