package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadersRepository extends CrudRepository<Reader,Long> {
}
