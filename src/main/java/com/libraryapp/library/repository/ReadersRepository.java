package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadersRepository extends JpaRepository<Reader, Long> {
    List<Reader> findAll();
}
