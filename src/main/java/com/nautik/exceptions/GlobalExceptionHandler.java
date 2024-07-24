package com.nautik.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Método para lidar com as exceções de produtos não encontradas
     * @param productNotFoundException
     * @return
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productNotFoundException.getMessage());
    }

}
