package com.thuan.identiy_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiErrorResponse {
    final int status;
    final List<FieldError> messages;
    final String path;
    final String timestamp;

    public ApiErrorResponse(int status, List<FieldError> messages, String path) {
        this.status = status;
        this.messages = messages;
        this.path = path;
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class FieldError {
        String field;
        String message;

        public FieldError(String message) {
            this.message = message;
        }
    }
}
