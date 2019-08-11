package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("update Book b set b.rented = :rented where b.id=:id")
    int updateBookSetRentedForId(@Param("rented") boolean rented, @Param("id") Long id);
}
