package com.flab.Mytube.error;

import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.error.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandlers extends RuntimeException{
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException err) {
    return ErrorMessage.toResponseEntity(HttpStatus.NOT_FOUND, err);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity handleUserNotFoundException(UserNotFoundException err){
    return ErrorMessage.toResponseEntity(HttpStatus.NOT_FOUND, err);
  }
}
