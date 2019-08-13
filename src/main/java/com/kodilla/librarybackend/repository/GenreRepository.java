package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository <Genre,Long> {

    @Modifying
    @Query("update Genre g set g.name = :name where g.id=:id")
    int updateGenreNameForId(@Param("name") String name, @Param("id") Long id);

}
