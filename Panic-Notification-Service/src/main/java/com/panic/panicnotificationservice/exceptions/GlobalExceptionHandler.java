package com.panic.panicnotificationservice.exceptions;

import com.panic.panicnotificationservice.exceptions.models.UnifiedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<UnifiedErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                UnifiedErrorResponse.builder()
                                .id("0001")
                                .message(e.getMessage())
                                .generalMessage("INTERNAL_SERVER_ERROR").build()
        );
    }

}
