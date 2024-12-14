package com.thuan.identiy_service.exception;

import com.thuan.identiy_service.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRunTimeException(RuntimeException exception, HttpServletRequest request) {
        exception.printStackTrace();
        ErrorCode errorCode = ErrorCode.UN_HANDLE_EXCEPTION;
        List<ApiErrorResponse.FieldError> errors = new ArrayList<>(List.of(new ApiErrorResponse.FieldError(errorCode.getMessage())));
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(400, errors, request.getRequestURI());
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppException(AppException exception, HttpServletRequest request) {
        ErrorCode errorCode = exception.getErrorCode();
        List<ApiErrorResponse.FieldError> errors = new ArrayList<>(List.of(new ApiErrorResponse.FieldError(errorCode.getMessage())));
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(errorCode.getHttpStatusCode().value(), errors, request.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiErrorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ApiErrorResponse.FieldError> errors = exception.getFieldErrors().stream()
                .map(fieldError -> new ApiErrorResponse.FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(400, errors, request.getRequestURI());
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException exception, HttpServletRequest request) {
        log.error("handleAuthorizationDeniedException");
        ErrorCode errorCode = ErrorCode.FORBIDEN_EXCEPTION;
        List<ApiErrorResponse.FieldError> errors = new ArrayList<>(List.of(new ApiErrorResponse.FieldError(errorCode.getMessage())));
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(errorCode.getHttpStatusCode().value(), errors, request.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiErrorResponse);
    }
}
