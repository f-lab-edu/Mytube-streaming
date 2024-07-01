package com.flab.Mytube.error;

import com.flab.Mytube.utils.HttpRequestUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
@Builder
public class ErrorMessage {
  private final String error;
  private final String message;
  private final int code;
  private final String path;

  public static ResponseEntity<ErrorMessage> toResponseEntity(HttpStatus httpStatus, Exception e) {
    return ResponseEntity.status(httpStatus)
        .body(
            ErrorMessage.builder()
                .error(httpStatus.name())
                .code(httpStatus.value())
                .message(e.getMessage())
                .path(HttpRequestUtils.getRequest().getRequestURI())
                .build());
  }
}
