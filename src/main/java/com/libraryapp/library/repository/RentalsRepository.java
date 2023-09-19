package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, Long> {
}
