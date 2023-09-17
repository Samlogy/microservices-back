package com.example.back.handler;


import com.example.back.exception.BadRequestionException;
import com.example.back.exception.NotFoundException;
import com.example.back.exception.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFound(
            NotFoundException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseError.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(BadRequestionException.class)
    public ResponseEntity<ResponseError> handleBadRequest(
            BadRequestionException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseError.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .build()
                );
    }

}
