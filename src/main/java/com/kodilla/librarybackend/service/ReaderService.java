package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.Reservation;
import com.kodilla.librarybackend.repository.ReaderRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!()])(?=\\S+$).{7,25}$";

    private Pattern pattern;
    private Matcher matcher;

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

    @Transactional
    public Reader saveReader(Reader reader){
        Reader savedReader = null;
        LOGGER.info("Starting creation of reader: "+ reader.toString());
        if (isEmailAddressValid(reader.getEmailAddress()) && (isPasswordCorrect(reader.getPassword()))) {
            savedReader = readerRepository.save(reader);
            LOGGER.info("Finished: reader creation");
        } else {
            LOGGER.info("Rejection of creating reader");
        }
        return savedReader;
        }


    private boolean isEmailAddressValid(String emailAddress) {
        boolean isEmailAddressValid = false;
        LOGGER.info("Starting: validation of email: " + emailAddress);
        if (EmailValidator.getInstance().isValid(emailAddress)) {
            isEmailAddressValid = true;
            LOGGER.info("Email is valid");
        } else {
            LOGGER.error("Invalid email address");
        }
        return isEmailAddressValid;
    }

    private boolean isPasswordCorrect(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        LOGGER.info("Starting validation of password: " + password);
        if (matcher.matches()) {
            LOGGER.info("Password correct");
        } else {
            LOGGER.error("Weak password. Password must contain 8-25 characters, at least 1 lower case letter, upper case letter, digit and special character");
        }
        return matcher.matches();
    }

    public boolean loginReader(String emailAddress, String password) {
        LOGGER.info("Login email: " + emailAddress + " login password: " + password);
        boolean ifLoginSuccessful = false;

        if (findReaderByLoginData(emailAddress, password).getId() != null) {
            Reader foundReader = findReaderByLoginData(emailAddress, password);
            LOGGER.info("User found uuid:" + foundReader.getId());
            if (foundReader.getPassword().equals(password)) {
                ifLoginSuccessful = true;
                LOGGER.info("Password '" + password +"' correct");
            }
        } else {
            LOGGER.error("Login failed. Reader does not exist or password is incorrect");
        }
        return ifLoginSuccessful;
    }

    /*public Reader changeData(Reader reader) {
        LOGGER.info("Starting: changing reader data to " + reader.toString());
        Reader readerToUpdate = findReaderByLoginData(reader.getEmailAddress(),reader.getPassword());
        LOGGER.info("Found user: " + readerToUpdate.toString());
        if(readerToUpdate != null){
            readerToUpdate.setEmailAddress(reader.getEmailAddress());
            readerToUpdate.setPhoneNumber(reader.getPhoneNumber());
            if (isPasswordCorrect(reader.getPassword())) {
                readerToUpdate.setPassword(reader.getPassword());
            }
            LOGGER.info("Data change successful");
        } else {
            LOGGER.error("Reader not found. Data change impossible");
        }
        return readerRepository.save(readerToUpdate);
    }*/

    public Reader findReaderByLoginData(String emailAddress, String password) {
        return Optional.ofNullable(readerRepository.findFirstByEmailAddressAndPassword(emailAddress, password)).orElse(new Reader());
    }

    public Reader findReaderByUuid(String uuid){
        return Optional.ofNullable(readerRepository.findFirstByUuid(uuid)).orElse(new Reader());
    }

    public List<Reader> getAllReaders() {
        LOGGER.info("Getting all readers");
        return readerRepository.findAll();
    }

    public Reader getReader(final Long id) {
        LOGGER.info("Getting reader with id:"+id.toString());
        return readerRepository.getOne(id);
    }

    /*public Reader createReader(final Reader reader) {
        LOGGER.info("Creating new reader");
        return readerRepository.save(reader);
    }*/

   /* public void blockReader(final Long id) {
        LOGGER.info("Blocking reader started,readerId:"+id.toString());
        readerRepository.updateReaderSetStatusForId(false, id);
        Reader reader = getReader(id);
        entityManager.refresh(reader);
        LOGGER.info("Finished: locking reader,readerId:"+id.toString());
    }*/

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
