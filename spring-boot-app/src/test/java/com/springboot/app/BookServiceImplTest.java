package com.springboot.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import com.springboot.app.entity.Book;
import com.springboot.app.entity.Category;
import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.repository.BookRepository;
import com.springboot.app.service.BookService;
import com.springboot.app.service.BookServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootAppApplication.class)
public class BookServiceImplTest {

  @Mock private BookRepository bookRepository;

  @InjectMocks private BookServiceImpl bookServiceImpl;

  @Mock private BookService bookService;

  @Mock private Category cat;

  @Mock private Book bks;

  private Book updatedBook;

  @BeforeEach
  void setUp() {
    @SuppressWarnings("deprecation")
    java.sql.Date sqlDate = new java.sql.Date(2020, 12, 12);

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

    updatedBook = new Book();
    updatedBook.setBookName("New Book Name");
    updatedBook.setBookAuthor("New Author");
    updatedBook.setBookPublishar("New Publisher");
    updatedBook.setBookPrice(30.00f);
    updatedBook.setCategory(cat);
  }

  @Test
  public void testSaveBook() {

    Mockito.when(this.bookRepository.save(Mockito.any(Book.class))).thenReturn(bks);
    Book book = bookServiceImpl.saveBook(bks);
    assertNotNull(book);
    assertEquals("sss", book.getBookName());
  }

  @Test
  void testFindByAll() {

    List<Book> book = new ArrayList<>();
    book.add(bks);
    Mockito.when(this.bookRepository.findAll()).thenReturn(book);
    List<Book> result = bookServiceImpl.findByAll();
    assertNotNull(result);
  }

  @Test
  void testFindById() {

    List<Book> book = new ArrayList<>();
    book.add(bks);
    Mockito.when(this.bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bks));
    List<Book> result = bookServiceImpl.findById(1l);
    assertNotNull(result);
  }

  @Test
  void testFindById_BookNotFound() {
    bks.setId(0l);
    Mockito.when(bookRepository.findById(0l)).thenReturn(Optional.ofNullable(null));
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> bookServiceImpl.findById(0l));
    assertEquals("Book not found with id : " + bks.getId(), exception.getMessage());
  }

  @Test
  void testFindByIdName() {
    List<Book> book = new ArrayList<>();
    book.add(bks);
    // Optional<Book> bks = new
    Mockito.when(this.bookRepository.findByIdName(Mockito.anyLong(), Mockito.anyString()))
        .thenReturn(Optional.of(bks));
    List<Book> result = bookServiceImpl.findByIdName(1l, bks.getBookName());
    assertNotNull(result);
  }

  @Test
  void testFindByIdName_BookNotFound() {
    bks.setBookName(null);
    bks.setId(0);

    // Mockito.when(bookRepository.findByIdName(Mockito.anyLong(),Mockito.anyString())).thenReturn(Optional.empty());
    // Verify that a ResourceNotFoundException is thrown
    ResourceNotFoundException exception =
        assertThrows(
            ResourceNotFoundException.class,
            () -> bookServiceImpl.findByIdName(1l, bks.getBookName()));

    // Assertions
    assertEquals("No Record for Id : 1 and BookName : null", exception.getMessage());
  }

  @Test
  void testUpdateBook() {
    List<Book> book = new ArrayList<>();
    book.add(bks);
    Mockito.when(this.bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bks));
    List<Book> result = bookServiceImpl.updateBook(1l, bks);
    verify(bookRepository).save(bks);

    // Assertions
    assertEquals(1, result.size());
    Book updatedResultBook = result.get(0);
    assertEquals("sss", updatedResultBook.getBookName());
    assertEquals("abc", updatedResultBook.getBookAuthor());
    assertEquals("100", updatedResultBook.getBookPublishar());
    assertEquals(1f, updatedResultBook.getBookPrice());
    assertEquals(cat, updatedResultBook.getCategory());

    assertNotNull(result);
  }

  @Test
  void testUpdateBook_BookNotFound() {
    // Mock the repository's findById method to return an empty Optional
    Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
    // Verify that a ResourceNotFoundException is thrown
    ResourceNotFoundException exception =
        assertThrows(
            ResourceNotFoundException.class, () -> this.bookServiceImpl.updateBook(1l, bks));

    // Assertions
    assertEquals("Book not found with id : 1", exception.getMessage());
  }
}
