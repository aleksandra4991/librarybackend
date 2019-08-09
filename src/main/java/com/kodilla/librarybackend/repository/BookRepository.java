package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
