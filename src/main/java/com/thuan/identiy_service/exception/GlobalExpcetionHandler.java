package com.thuan.identiy_service.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExpcetionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleRunTimeExpcetion(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        Map<String, String> error = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            error.put(fieldError.getField(), fieldError.getDefaultMessage() );
        }

        return ResponseEntity.badRequest().body(error);
    }


}
