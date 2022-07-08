package com.awakelife93.kafka.error;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestControllerAdvice
public class ErrorHandler {

  private HashMap<String, Object> getResponseErrorMap(HttpStatus status, String message) {
    HashMap<String, Object> responseErrorMap = new HashMap<String, Object>();

    responseErrorMap.put("status", status.value());
    responseErrorMap.put("message", message);

    return responseErrorMap;
  }

  @ExceptionHandler(InterruptedException.class)
  private ResponseEntity<HashMap<String, Object>> interruptedExceptionHandler(Throwable throwable) {
    System.out.println("============= interruptedExceptionHandler Error =============" + " : " + new Date().getTime());

    HashMap<String, Object> responseErrorMap = getResponseErrorMap(HttpStatus.INTERNAL_SERVER_ERROR,
        throwable.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorMap);
  }

  @ExceptionHandler(ExecutionException.class)
  private ResponseEntity<HashMap<String, Object>> executionExceptionHandler(Throwable throwable) {
    System.out.println("============= executionExceptionHandler Error =============" + " : " + new Date().getTime());

    HashMap<String, Object> responseErrorMap = getResponseErrorMap(HttpStatus.INTERNAL_SERVER_ERROR,
        throwable.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorMap);
  }
}
