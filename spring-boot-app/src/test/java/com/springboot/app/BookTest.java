package com.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springboot.app.entity.Book;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class BookTest {

  private Book book;

  @Mock private Date created_dt;

  @Mock private Date modify_dt;

  @BeforeEach
  void setUp() {
    // created_td="2024-12-12";
    book = new Book();

    book.setCreated_dt(created_dt);
    book.setModify_dt(modify_dt);
  }

  @Test
  void testGetCreated_dt() {

    Date dt = book.getCreated_dt();
    assertEquals(created_dt, dt);
  }

  @Test
  void testGetModify_dt() {
    Date dt = book.getModify_dt();
    assertEquals(modify_dt, dt);
  }
}
