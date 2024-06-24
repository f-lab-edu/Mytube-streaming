package com.flab.Mytube.error.exceptions;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
