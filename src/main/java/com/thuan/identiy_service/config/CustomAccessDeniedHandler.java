package com.thuan.identiy_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuan.identiy_service.dto.response.ApiErrorResponse;
import com.thuan.identiy_service.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.List;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("CustomAccessDeniedHandler");
        ErrorCode errorCode = ErrorCode.FORBIDEN_EXCEPTION;
        response.setStatus(errorCode.getHttpStatusCode().value());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(errorCode.getHttpStatusCode().value(),
                List.of(ApiErrorResponse.FieldError.builder().message(errorCode.getMessage()).build())
                , request.getRequestURI());

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiErrorResponse));
        response.flushBuffer();
    }
}
