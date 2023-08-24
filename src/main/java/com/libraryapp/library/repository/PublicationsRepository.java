package com.libraryapp.library.repository;

import com.libraryapp.library.domain.Publications;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsRepository extends CrudRepository<Publications,Long> {

}
