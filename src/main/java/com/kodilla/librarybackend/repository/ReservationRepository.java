package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Modifying
    @Query("update Reservation r set r.active = :active where r.id=:id")
    int updateReservationSetRentedForId(@Param("active") AtomicBoolean active, @Param("id") Long id);
}
