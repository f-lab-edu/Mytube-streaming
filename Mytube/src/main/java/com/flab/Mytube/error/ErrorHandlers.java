package com.flab.Mytube.error;

import com.flab.Mytube.error.exceptions.AlreadyEndedLiveException;
import com.flab.Mytube.error.exceptions.DuplicatedPathException;
import com.flab.Mytube.error.exceptions.InvalidFileExtension;
import com.flab.Mytube.error.exceptions.NoDataSubmitException;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.error.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlers {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException err) {
    return ErrorMessage.toResponseEntity(HttpStatus.NOT_FOUND, err);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity handleUserNotFoundException(UserNotFoundException err) {
    return ErrorMessage.toResponseEntity(HttpStatus.NOT_FOUND, err);
  }

  @ExceptionHandler(DuplicatedPathException.class)
  public ResponseEntity handleDuplicatedPath(DuplicatedPathException err) {
    return ErrorMessage.toResponseEntity(HttpStatus.CONFLICT, err);
  }

  @ExceptionHandler(NoDataSubmitException.class)
  public ResponseEntity handleNoDataSubmitException(NoDataSubmitException err) {
    return ErrorMessage.toResponseEntity(HttpStatus.CONFLICT, err);
  }

  @ExceptionHandler(InvalidFileExtension.class)
  public ResponseEntity handleInvalidFileExtension(InvalidFileExtension err) {
    return ErrorMessage.toResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, err);
  }

  @ExceptionHandler(AlreadyEndedLiveException.class)
  public ResponseEntity handleAlreadyEndedLiveException(AlreadyEndedLiveException err) {
    return ErrorMessage.toResponseEntity(HttpStatus.BAD_REQUEST, err);
  }
}
