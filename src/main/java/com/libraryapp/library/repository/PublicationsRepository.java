package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationsRepository extends JpaRepository<Publications, Long> {
    boolean existsByTitle(String title);

    Optional<Publications> findByTitle(String title);

    List<Publications> findAll();
}
