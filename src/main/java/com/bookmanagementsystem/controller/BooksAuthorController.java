package com.bookmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagementsystem.entity.Author;
import com.bookmanagementsystem.entity.Books;
import com.bookmanagementsystem.repo.IAuthor;

@RestController
public class BooksAuthorController {
   
	@Autowired
	private IAuthor bookAuthor;
	
	@GetMapping("/welcome")
	public ResponseEntity<String> sayHello(){
		return new ResponseEntity<>("hello",HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value= "/save/authorbooks",produces = {"application/json"})
	public ResponseEntity<Optional<Author>> saveAuthorBooks(@Valid @RequestBody Author authorBook){
		ResponseEntity<Optional<Author>> responseEntity = null;

	
		Optional<Author> saveAuthorBook = 
				Optional.ofNullable(bookAuthor.save(authorBook));
		
		   if(saveAuthorBook.isEmpty())
			   responseEntity = new  
			   ResponseEntity<>(saveAuthorBook,HttpStatus.NOT_ACCEPTABLE);
		   else
			   responseEntity = new  
			   ResponseEntity<>(saveAuthorBook,HttpStatus.ACCEPTED);
		
		   return responseEntity;
	}
	
	
	
	@GetMapping("/all/authorandbooks")
	public ResponseEntity<Optional<List<Author>>> getAllAuthorBook(){
        ResponseEntity<Optional<List<Author>>> responceEntity =null;
        
		Optional<List<Author>> allResult = Optional.
				ofNullable(bookAuthor.findAll());
		
		if(allResult.isEmpty()) {
		  responceEntity = new ResponseEntity<>(allResult,HttpStatus.NOT_FOUND);	
		  return responceEntity;
		}
		responceEntity = new ResponseEntity<>(allResult,HttpStatus.ACCEPTED);
		return responceEntity;
	}
	
	
	@GetMapping("/author/{id}")
	public ResponseEntity<Optional<Author>> getBooksByAuthor(@PathVariable("id") Integer authorId){
		ResponseEntity<Optional<Author>> responce =null;
		
	    Optional<Author> author = bookAuthor.findById(authorId);
	    
	    if(author.isEmpty())
	    	responce = new ResponseEntity<>(author,HttpStatus.NOT_FOUND);
	    else
	     responce = new ResponseEntity<>(author,HttpStatus.OK);
	    
	    return responce;
	}
	
	@GetMapping("/all/author/byname/match/{name}")
	public ResponseEntity<List<Optional<Author>>> getAuthorByName(
			@PathVariable("name") String authorName){
		ResponseEntity<List<Optional<Author>>> responce =null;
		
		List<Optional<Author>> findByAuthorNameContaining = bookAuthor.findByAuthorNameContaining(authorName);
		if(findByAuthorNameContaining.isEmpty())
			responce = new 
			ResponseEntity<>(findByAuthorNameContaining,HttpStatus.NOT_FOUND);
		else
			responce =
			new ResponseEntity<>(findByAuthorNameContaining,HttpStatus.OK);
		
		return responce;
	}
	@PutMapping("/update/author/contact/{id}/{name}/{contact}")
	public ResponseEntity<Optional<Author>> updateContactByAuthorNameId(@PathVariable("id") Integer authorId ,
			@PathVariable("name") String authorName,
			@PathVariable("contact") String authContact){
		ResponseEntity<Optional<Author>> responce=null;
		
		Optional<Author> findByIdAndName = bookAuthor.findByIdAndAuthorName(authorId, authorName);
		if(findByIdAndName.isEmpty()) {
			responce = new ResponseEntity<>(findByIdAndName,HttpStatus.NOT_FOUND);
		}
		else {
			Author author = findByIdAndName.get();
			author.setContactNumber(authContact);
			bookAuthor.save(author);
			responce = new ResponseEntity<>(findByIdAndName,HttpStatus.OK);
		}
		return responce;
	}
	
	

	@PutMapping("/update/author/email/{id}/{name}/{email}")
	public ResponseEntity<Optional<Author>> updateEmailByAuthorNameId(@PathVariable("id") Integer authorId ,
			@PathVariable("name") String authorName,
			@PathVariable("email") String authorEmail){
		ResponseEntity<Optional<Author>> responce=null;
		
		Optional<Author> findByIdAndName = bookAuthor.findByIdAndAuthorName(authorId, authorName);
		if(findByIdAndName.isEmpty()) {
			responce = new ResponseEntity<>(findByIdAndName,HttpStatus.NOT_FOUND);
		}
		else {
			Author author = findByIdAndName.get();
			author.setEmail(authorEmail);
			bookAuthor.save(author);
			responce = new ResponseEntity<>(findByIdAndName,HttpStatus.OK);
		}
		return responce;
	}
	

	@PutMapping("/update/author/address/{id}/{name}/{address}")
	public ResponseEntity<Optional<Author>> updateByAuthorNameId(@PathVariable("id") Integer authorId ,
			@PathVariable("name") String authorName,
			@PathVariable("address") String authorAddress){
		ResponseEntity<Optional<Author>> responce=null;
		
		Optional<Author> findByIdAndName = bookAuthor.findByIdAndAuthorName(authorId, authorName);
		if(findByIdAndName.isEmpty()) {
			responce = new ResponseEntity<>(findByIdAndName,HttpStatus.NOT_FOUND);
		}
		else {
			Author author = findByIdAndName.get();
			author.setAddress(authorAddress);
			bookAuthor.save(author);
			responce = new ResponseEntity<>(findByIdAndName,HttpStatus.OK);
		}
		return responce;
	}
	
	
	@DeleteMapping("/delete/author/{id}/{name}")
	public ResponseEntity<Optional<Author>> deleteAuthorByIdName(
			@PathVariable("id") Integer id,
			@PathVariable("name") String authorName){

		 ResponseEntity<Optional<Author>> responce=null;
		 
		   Optional<Author> findByIdAndAuthorName = bookAuthor.findByIdAndAuthorName(id, authorName);
		
		   if(!findByIdAndAuthorName.isEmpty()) {
			Author author = findByIdAndAuthorName.get();
			bookAuthor.delete(author);
			responce = 
					new ResponseEntity<>(findByIdAndAuthorName,HttpStatus.OK);
		   }
		   else {
			  responce = 
					  new ResponseEntity<>(findByIdAndAuthorName,HttpStatus.NOT_FOUND); 
		   }
		   return responce;
	}
	
	@GetMapping("/all/books/author/{name}")
	public ResponseEntity<List<Optional<Books>>> getBooksByAuthorName(@PathVariable("name")
	 String authorName){
		ResponseEntity<List<Optional<Books>>> responce=null;
		
		List<Optional<Books>> allBooksByAuthor = bookAuthor.getAllBooksByAuthor(authorName);
		if(allBooksByAuthor.isEmpty()) {
			responce = 
					new ResponseEntity<>(allBooksByAuthor,HttpStatus.NOT_FOUND);
		}
		else {
			responce = 
					new ResponseEntity<>(allBooksByAuthor,HttpStatus.OK);
		}
		return responce;
	}
	
	
	@GetMapping("/all/authors/book/{bookname}")
	public ResponseEntity<List<Optional<Author>>> getAuthorsByBookName(@PathVariable("bookname") String bookName){
		
		ResponseEntity<List<Optional<Author>>> respone = null;
		List<Optional<Author>> allAuthorByBook = bookAuthor.getAllAuthorByBook(bookName);
		
		if(allAuthorByBook.isEmpty())
			respone = new ResponseEntity<>(allAuthorByBook,HttpStatus.NOT_FOUND);
		else
			respone = new ResponseEntity<>(allAuthorByBook,HttpStatus.OK);
		
		return respone;
	}
}
