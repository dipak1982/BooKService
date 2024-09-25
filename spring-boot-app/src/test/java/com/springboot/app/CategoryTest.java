package com.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springboot.app.entity.Category;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CategoryTest {

  private Category cat;

  @Mock private Date created_dt;

  @Mock private Date modify_dt;

  @BeforeEach
  void setUp() {
    // created_td="2024-12-12";
    cat = new Category();
    cat.setId(123L);
    cat.setCatName("");
    cat.setCreated_dt(created_dt);
    cat.setModify_dt(modify_dt);
  }

  @Test
  void testGetId() {
    long id = cat.getId();
    assertEquals(123L, id);
  }

  @Test
  void testGetCatName() {
    String name = cat.getCatName();
    assertEquals("", name);
  }

  @Test
  void testGetCreated_td() {

    Date dt = cat.getCreated_dt();
    assertEquals(created_dt, dt);
  }

  @Test
  void testGetModify_dt() {
    Date dt = cat.getModify_dt();
    assertEquals(modify_dt, dt);
  }
}
