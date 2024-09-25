package com.user.registration.Exception;

import static org.junit.Assert.assertNotNull;

import com.user.registration.entity.ErrorMessage;
import com.user.registration.exception.ControllerExceptionHandler;
import com.user.registration.exception.ResourceNotFoundException;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@SpringBootTest
public class ControllerExceptionHandlerTest {

  @Mock private ErrorMessage message;

  @InjectMocks private ControllerExceptionHandler controllerExceptionHandler;

  @Mock private ResourceNotFoundException ex;

  @Mock private WebRequest request;

  @Test
  public void resourceNotFoundException() {

    message =
        new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

    ResponseEntity<ErrorMessage> result =
        controllerExceptionHandler.resourceNotFoundException(ex, request);
    assertNotNull(result);
  }

  @Test
  public void globalExceptionHandler() {
    message =
        new ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    ResponseEntity<ErrorMessage> result =
        controllerExceptionHandler.globalExceptionHandler(ex, request);
    assertNotNull(result);
  }
}
