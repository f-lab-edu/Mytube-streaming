package com.flab.Mytube.error.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
