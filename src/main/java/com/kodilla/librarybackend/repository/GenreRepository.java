package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository <Genre,Long> {

}
