package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.Reservation;
import com.kodilla.librarybackend.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(final Long id) {
        return readerRepository.getOne(id);
    }

    public Reader createReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public void blockReader(final Long id) {
        readerRepository.updateReaderSetStatusForId(false, id);
        Reader reader = getReader(id);
        entityManager.refresh(reader);
    }

    public List<Reservation> getReservationsOfSpecifiedReader(long id){
        List<Reservation> reservationsOfSpecifiedReader = readerRepository.findById(id).get().getReservations();
        return reservationsOfSpecifiedReader;
    }

    public List<Book> getRentedBooksOfSpecifiedReader(long id){
        List<Book> booksRentedBySpecifiedReader = readerRepository.findById(id).get().getBookList();
        return booksRentedBySpecifiedReader;
    }

    @Transactional
    public void deleteReader(final Long id){
        readerRepository.deleteById(id);
    }
}
