package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {


    @Query("SELECT b " +
            "FROM Borrow  b " +
            "WHERE b.reader = :userId")
    List<Borrow> findAllByReader(Long userId);


}
