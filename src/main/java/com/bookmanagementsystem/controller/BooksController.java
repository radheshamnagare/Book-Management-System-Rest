package com.bookmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagementsystem.entity.Books;
import com.bookmanagementsystem.repo.IBooks;

@RestController
public class BooksController {

	@Autowired
	private IBooks ibook;
	
	@GetMapping("/book/{bookid}")
	public ResponseEntity<Optional<Books>> getBookById(@PathVariable("bookid") Integer bookId){
		ResponseEntity<Optional<Books>> responce=null;
	   
		Optional<Books> book = ibook.findById(bookId);
		if(book.isEmpty())
			responce = new ResponseEntity<>(book,HttpStatus.NOT_FOUND);
		else
			responce = new ResponseEntity<>(book,HttpStatus.OK);
		
		return responce;
	}
	
	@PutMapping("/update/{id}/{name}")
	public ResponseEntity<Optional<Books>> updateBooksName(@PathVariable("id") Integer bookId,
			@PathVariable("name") String bookName){
		ResponseEntity<Optional<Books>> responce=null;
		
		Optional<Books> book = ibook.findById(bookId);
		if(!book.isEmpty()) {
			Books b  = book.get();
			b.setBookName(bookName);
			ibook.save(b);
			responce = new ResponseEntity<>(book,HttpStatus.OK);
		}
		else {
			responce = new ResponseEntity<>(book,HttpStatus.NOT_FOUND);
		}
		
		return responce;
	}
	
	@PutMapping("/update/{id}/{price}")
	public ResponseEntity<Optional<Books>> updateBooksPrice(@PathVariable("id") Integer bookId,
			@PathVariable("price") Double bookprice){
		ResponseEntity<Optional<Books>> responce=null;
		
		Optional<Books> book = ibook.findById(bookId);
		if(!book.isEmpty()) {
			Books b  = book.get();
			b.setPrice(bookprice);
			ibook.save(b);
			responce = new ResponseEntity<>(book,HttpStatus.OK);
		}
		else {
			responce = new ResponseEntity<>(book,HttpStatus.NOT_FOUND);
		}
		
		return responce;
	}
	
	@GetMapping("/all/books")
	public  ResponseEntity<Optional<List<Books>>> getBooks(){
		ResponseEntity<Optional<List<Books>>> responce=null;
		
		Optional<List<Books>> books = Optional.ofNullable(ibook.findAll());
	    if(books.isEmpty())
	    	responce=new ResponseEntity<>(books,HttpStatus.OK);
	    else
	    	responce = new ResponseEntity<>(books,HttpStatus.NOT_FOUND);
	    
		return responce;
	}
	
	@DeleteMapping("/delete/book/{id}")
	public ResponseEntity<Optional<Books>> deleteBooks(@PathVariable("id") Integer bookId){
		ResponseEntity<Optional<Books>> responce=null;
		
		Optional<Books> book = ibook.findById(bookId);
		
		if(!book.isEmpty()) {
			ibook.delete(book.get());
            responce = new ResponseEntity<>(book,HttpStatus.OK);
		}
		else {
			responce = new ResponseEntity<>(book,HttpStatus.NOT_FOUND);
		}
		return responce;
	}
}
