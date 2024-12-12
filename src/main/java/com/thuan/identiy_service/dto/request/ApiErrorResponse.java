package com.thuan.identiy_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private int status;
    private List<FieldError> messages;
    private String path;
    private String timestamp;
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
    public static class FieldError {
        private String field;
        private String message;
        public FieldError(String message) {
            this.message = message;
        }
    }
}
