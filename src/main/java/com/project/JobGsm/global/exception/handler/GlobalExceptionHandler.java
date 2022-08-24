package com.project.JobGsm.global.exception.handler;

import com.project.JobGsm.global.exception.ErrorCode;
import com.project.JobGsm.global.exception.ErrorResponse;
import com.project.JobGsm.global.exception.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> DuplcateEmailException(HttpServletRequest request, DuplicateEmailException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorResponse> PasswordNotMatchException(HttpServletRequest request, PasswordNotMatchException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> InvalidTokenException(HttpServletRequest request, InvalidTokenException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> ExpiredTokenException(HttpServletRequest request, ExpiredTokenException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }


    public void printException(HttpServletRequest request, ErrorCode errorCode) {
        log.error(request.getRequestURI() + " 에서 { " + errorCode + " } 발생");
    }
}
