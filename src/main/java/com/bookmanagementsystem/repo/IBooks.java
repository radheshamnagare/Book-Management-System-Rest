package com.bookmanagementsystem.repo;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookmanagementsystem.entity.Books;

public interface IBooks extends JpaRepository<Books,Serializable> {

	Optional<Books> findByIdAndBookName(Integer bookId, String bookName);
	
}
