package com.bookmanagementsystem.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmanagementsystem.entity.Author;
import com.bookmanagementsystem.entity.Books;

public interface IAuthor extends JpaRepository<Author,Serializable>{

	boolean existsByAuthorName(String authorName);

	Optional<Author> findByIdAndAuthorName(Integer id,String name); 
	
	@Query("SELECT bs FROM Author a JOIN a.books bs where a.authorName= :authorName")
	List<Optional<Books>> getAllBooksByAuthor(String authorName);
	
	@Query("SELECT a FROM Author a JOIN a.books b where b.bookName= :bookName")
	List<Optional<Author>> getAllAuthorByBook(String bookName);

	List<Optional<Author>> findByAuthorNameContaining(String authorName);
	
	
}
