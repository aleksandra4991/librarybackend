package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository <Reader,Long> {
}
