package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Publications;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationsRepository extends CrudRepository<Publications, Long> {
    boolean existsByTitle(String title);

    Optional<Publications> findByTitle(String name);

    @Modifying
    @Query("DELETE FROM Publications p WHERE p.title = :title")
    void deleteByTitle(@Param("title") String title);

    List<Publications> findAll();
}
