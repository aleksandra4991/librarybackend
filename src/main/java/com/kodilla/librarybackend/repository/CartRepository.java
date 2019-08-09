package com.kodilla.librarybackend.repository;


import com.kodilla.librarybackend.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long> {
}
