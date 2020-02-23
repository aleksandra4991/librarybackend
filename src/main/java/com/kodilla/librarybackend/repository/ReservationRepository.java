package com.kodilla.librarybackend.repository;

import com.kodilla.librarybackend.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Modifying
    @Query("update Reservation r set r.active = :active where r.id=:id")
    int updateReservationSetRentedForId(@Param("active") boolean active, @Param("id") Long id);
}
