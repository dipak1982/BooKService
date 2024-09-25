package com.springboot.app.controller;

import com.springboot.app.entity.Book;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*") // Allow requests from this origin
public class BookController {

  @Autowired private BookService bookService;

  @PostMapping("/savebook")
  public ResponseEntity<Book> saveBook(@RequestBody Book book) {
    Book bookResponse = null;
    if (book.getBookName() == null) {
      throw new ResourceNotFoundException("Book Name cant be null or empty");
    } else {
      bookResponse = this.bookService.saveBook(book);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
  }

  @GetMapping("/getAllBooks")
  public ResponseEntity<List<Book>> getBookDetails() {
    List<Book> result = this.bookService.findByAll();
    if (result.isEmpty()) {
      throw new ResourceNotFoundException("No books available");
    }
    return ResponseEntity.ok(result);
  }

  @GetMapping("/findById/{id}")
  public ResponseEntity<List<Book>> getBookById(@PathVariable Long id) {
    List<Book> result = null;
    if (id == 0) {
      throw new ResourceNotFoundException("Id cant be zero or null");
    } else {
      result = this.bookService.findById(id);
    }
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
  }

  @GetMapping("/findByIdName")
  public ResponseEntity<List<Book>> getBookByIdName(@RequestBody Book book) {
    List<Book> result = this.bookService.findByIdName(book.getId(), book.getBookName());
    if (result.isEmpty()) {
      throw new ResourceNotFoundException("No books found with the provided Id and Name");
    }
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
  }

  @PutMapping("/updateBook/{id}")
  public ResponseEntity<List<Book>> updateBookDetails(
      @PathVariable Long id, @RequestBody Book book) {
    List<Book> bookList = this.bookService.updateBook(id, book);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookList);
  }

  @GetMapping("/findAllBookId")
  public ResponseEntity<List> findAllBookId() {
    List list = this.bookService.findAllBookId();
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
  }

  @DeleteMapping("/deleteById/{id}")
  public void delete(@PathVariable long id) {
    this.bookService.deleteById(id);
  }
}
