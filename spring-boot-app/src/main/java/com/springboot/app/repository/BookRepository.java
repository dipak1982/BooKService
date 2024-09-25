package com.springboot.app.repository;

import com.springboot.app.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("SELECT b FROM Book b WHERE b.id = :id AND b.bookName = :bookName")
  Optional<Book> findByIdName(Long id, String bookName);

  @Query("SELECT b FROM Book b")
  List<Book> findAllBookId();
}
