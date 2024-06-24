package com.flab.Mytube.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpRequestUtils {
  public static HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
}
