package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadersRepository extends CrudRepository<Reader,Long> {
    List<Reader>findAll();
}
