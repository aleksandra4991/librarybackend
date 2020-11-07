package com.kodilla.librarybackend.repository;
import com.kodilla.librarybackend.domain.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolumeRepository extends JpaRepository <Volume, Long> {

     /*@Modifying
    @Query("update Book b set b.rented = :rented where b.id=:id")
    int updateBookSetRentedForId(@Param("rented") boolean rented, @Param("id") Long id);*/

    Volume findFirstByTitle(String title);
    Volume findFirstByAuthors(String authors);
    List <Volume> findFirstByTitleAndAuthors(String title, String author);
}
