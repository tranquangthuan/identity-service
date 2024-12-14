package com.thuan.identiy_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "user existed"),
    USER_NOT_EXISTED(1002, "user not existed"),
    UN_HANDLE_EXCEPTION(1003, "Un expected exception"),
    UN_AUTHENTICATED(1004, "Un authenticated");
    private int code;
    private String message;
}
