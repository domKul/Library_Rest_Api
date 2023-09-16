package com.libraryapp.library.repository;

import com.libraryapp.library.domain.BookCopies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopies, Long> {

}
