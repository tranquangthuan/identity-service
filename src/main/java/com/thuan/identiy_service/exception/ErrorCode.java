package com.thuan.identiy_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "user existed", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_EXISTED(1002, "user not existed", HttpStatus.NOT_FOUND),
    UN_HANDLE_EXCEPTION(1003, "Un expected exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UN_AUTHENTICATED(1004, "Un authenticated", HttpStatus.UNAUTHORIZED),
    FORBIDEN_EXCEPTION(1005, "Forbiden exception", HttpStatus.FORBIDDEN);
    private int code;
    private String message;
    private HttpStatus httpStatusCode;
}
