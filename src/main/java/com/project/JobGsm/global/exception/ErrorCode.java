package com.project.JobGsm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* USER */
    DUPLICATE_EMAIL(BAD_REQUEST, "중복된 이메일 입니다."),
    USER_NOT_FOUND(NOT_FOUND, "사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(BAD_REQUEST, "비밀번호가 일치하지 않습니다."),


    /* TOKEN */
    INVALID_TOKEN(FORBIDDEN, "유효하지 않은 토큰입니다."),
    EXFIRED_TOKEN(FORBIDDEN, "만료된 토큰입니다."),
    ;

    private HttpStatus httpStatus;
    private String message;
}