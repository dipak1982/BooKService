package com.springboot.app;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.springboot.app.controller.BookController;
import com.springboot.app.entity.Book;
import com.springboot.app.entity.Category;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.service.BookService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = SpringBootAppApplication.class)
public class BookControllerTest {

  @Mock private BookService bookService;

  @InjectMocks private BookController bookController;

  @Mock private Category cat;

  @Mock private Book bks;

  @BeforeEach
  void setUp() {
    @SuppressWarnings("deprecation")
    java.sql.Date sqlDate = new java.sql.Date(2024, 12, 12);

    float f = 1;
    long l = 1l;
    cat = new Category();
    cat.setCatName("abc");
    cat.setCreated_dt(sqlDate);
    cat.setModify_dt(sqlDate);

    bks = new Book();
    bks.setBookAuthor("abc");
    bks.setBookName("sss");
    bks.setBookPublishar("100");
    bks.setBookPrice(f);
    bks.setCreated_dt(sqlDate);
    bks.setModify_dt(sqlDate);
    bks.setId(l);
    bks.setCategory(cat);
  }

  @Test
  public void testSaveBook() throws Exception {
    Mockito.when(this.bookService.saveBook(Mockito.any(Book.class))).thenReturn(bks);
    ResponseEntity<Book> result = this.bookController.saveBook(bks);
    assertNotNull(result.getBody());
    assertEquals("sss", result.getBody().getBookName());
  }

  @Test
  void testSaveBook_BookNameIsNull() {
    bks.setBookName(null);
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> this.bookController.saveBook(bks));
    assertEquals("Book Name cant be null or empty", exception.getMessage());
  }

  @Test
  void testGetBookDetails() {

    List<Book> book = new ArrayList<>();
    book.add(bks);
    Mockito.when(this.bookService.findByAll()).thenReturn(book);
    ResponseEntity<List<Book>> result = this.bookController.getBookDetails();
    assertNotNull(result.getBody());
    assertEquals("sss", result.getBody().get(0).getBookName());
  }

  @Test
  void testFindAll_ResponseIsNull() {
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> this.bookController.getBookDetails());
    assertEquals("No books available", exception.getMessage());
  }

  @Test
  public void testGetBookById() {

    List<Book> book = new ArrayList<>();
    book.add(bks);

    Mockito.when(this.bookService.findById(Mockito.anyLong())).thenReturn(book);
    ResponseEntity<List<Book>> result = this.bookController.getBookById(1l);
    assertNotNull(result.getBody());
    assertEquals("sss", result.getBody().get(0).getBookName());
  }

  @Test
  void testFindById_IdIsZero() {
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> bookController.getBookById(0l));
    assertEquals("Id cant be zero or null", exception.getMessage());
  }

  @Test
  void testGetBookByIdName() {
    List<Book> book = new ArrayList<>();
    book.add(bks);
    Mockito.when(this.bookService.findByIdName(Mockito.anyLong(), Mockito.anyString()))
        .thenReturn(book);
    ResponseEntity<List<Book>> result = this.bookController.getBookByIdName(bks);
    assertNotNull(result);
    assertEquals("sss", result.getBody().get(0).getBookName());
  }

  @Test
  void testGetBookByIdNameThrowsExceptionForNoBooksFound() {
    Book book = new Book();
    book.setId(0);
    book.setBookName("");
    Mockito.when(bookService.findByIdName(Mockito.anyLong(), Mockito.anyString()))
        .thenReturn(Collections.emptyList());
    ResourceNotFoundException thrown =
        assertThrows(
            ResourceNotFoundException.class, () -> this.bookController.getBookByIdName(book));
    assertEquals("No books found with the provided Id and Name", thrown.getMessage());
  }

  @Test
  void testUpdateBookDetails() {
    List<Book> book = new ArrayList<>();
    book.add(bks);
    Mockito.when(this.bookService.updateBook(Mockito.anyLong(), Mockito.any())).thenReturn(book);
    ResponseEntity<List<Book>> result = this.bookController.updateBookDetails(1l, bks);
    assertNotNull(result);
  }
}
