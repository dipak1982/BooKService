package com.springboot.app.service;

import com.springboot.app.entity.Book;
import java.util.List;

public interface BookService {

  Book saveBook(Book book);

  List<Book> findByAll();

  List<Book> findById(Long id);

  List<Book> findByIdName(Long id, String bookName);

  List<Book> updateBook(Long id, Book book);

  List findAllBookId();

  void deleteById(long id);
}
