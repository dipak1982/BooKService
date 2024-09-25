package com.user.registration.entity;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorMessageTest {

  private ErrorMessage errorMessage;

  private int statusCode;
  private Date timestamp;
  private String message;
  private String description;

  @BeforeEach
  void setUp() {
    statusCode = 0;
    timestamp = null;
    errorMessage = new ErrorMessage(statusCode, null, null, null);
  }

  @Test
  void testGetStatusCode() {
    int statusCode = errorMessage.getStatusCode();
    assertEquals(0, statusCode);
  }

  @Test
  void testGetTimestamp() {
    Date timestamp = errorMessage.getTimestamp();
    assertEquals(null, timestamp);
  }

  @Test
  void testGetMessage() {
    String message = errorMessage.getMessage();
    assertEquals(null, message);
  }

  @Test
  void testGetDescription() {
    String description = errorMessage.getDescription();
    assertEquals(null, description);
  }
}
