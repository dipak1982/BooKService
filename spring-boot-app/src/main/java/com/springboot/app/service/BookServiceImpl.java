package com.springboot.app.service;

import com.springboot.app.entity.Book;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  @Autowired private BookRepository bookRepository;

  @Override
  public Book saveBook(Book book) {

    Book result = bookRepository.save(book);
    return result;
  }

  @Override
  public List<Book> findByAll() {
    // TODO Auto-generated method stub
    return bookRepository.findAll();
  }

  @Override
  public List<Book> findById(Long id) {
    // TODO Auto-generated method stub
    Optional<Book> result =
        Optional.of(
            this.bookRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Book not found with id : " + id)));
    List<Book> bookList = result.stream().collect(Collectors.toList());
    return bookList;
  }

  @Override
  public List<Book> findByIdName(Long id, String bookName) {
    // TODO Auto-generated method stub
    Optional<Book> result =
        Optional.of(
            this.bookRepository
                .findByIdName(id, bookName)
                .orElseThrow(
                    () ->
                        new ResourceNotFoundException(
                            "No Record for Id : " + id + " and BookName : " + bookName)));
    return result.stream().collect(Collectors.toList());
  }

  @Override
  public List<Book> updateBook(Long id, Book bookUpdate) {
    // TODO Auto-generated method stub
    Optional<Book> bookOptional = this.bookRepository.findById(id);
    if (bookOptional.isPresent()) {
      /* update book details*/
      Book book = bookOptional.get();
      book.setBookName(bookUpdate.getBookName());
      book.setBookAuthor(bookUpdate.getBookAuthor());
      book.setBookPublishar(bookUpdate.getBookPublishar());
      book.setBookPrice(bookUpdate.getBookPrice());
      book.setCategory(bookUpdate.getCategory());
      bookRepository.save(book);
    } else {
      throw new ResourceNotFoundException("Book not found with id : " + id);
    }
    return bookRepository.findById(id).stream().toList();
  }

  @Override
  public List findAllBookId() {
    List result = this.bookRepository.findAllBookId();
    return result;
  }

  @Override
  public void deleteById(long id) {
    this.bookRepository.deleteById(id);
  }
}
