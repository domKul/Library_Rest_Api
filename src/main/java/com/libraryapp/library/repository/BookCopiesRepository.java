package com.libraryapp.library.repository;

import com.libraryapp.library.domain.BookCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopiesRepository extends JpaRepository<BookCopies, Long> {

    @Query("SELECT COUNT(bc) FROM BookCopies bc WHERE bc.status = 'Available' AND bc.publications = :publicationId")
    Long countAvailableCopiesByTitle(long  publicationId);




}
