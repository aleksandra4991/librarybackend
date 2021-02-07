package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository <Reader,Long> {

    @Modifying
    @Query("update Reader r set r.status = :status where r.id=:id")
    int updateReaderSetStatusForId(@Param("status") boolean status, @Param("id") Long id);

    Reader findByEmailAddressAndPassword(String emailAddress,String password);
    Reader findFirstByEmailAddress(String emailAddress);
    Reader findFirstByPassword(String password);
    Reader findFirstByUuid(String uuid);

}
