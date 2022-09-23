package com.project.JobGsm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* USER */
    DUPLICATE_EMAIL(409, "중복된 이메일 입니다."),
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(400, "비밀번호가 일치하지 않습니다."),
    KEY_NOT_CORRECT(400, "인증 키가 일치하지 않습니다."),


    /* TOKEN */
    INVALID_TOKEN(403, "유효하지 않은 토큰입니다."),
    EXFIRED_TOKEN(403, "만료된 토큰입니다."),
    REFRESH_TOKEN_EXPIRATION(403, "만료된 refreshToken 입니다."),


    /* BOARD */
    BOARD_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    NO_BOARD(404, "게시글이 하나도 없습니다."),


    /* COMMENT */
    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없습니다."),
    ;

    private int status;
    private String message;
}
