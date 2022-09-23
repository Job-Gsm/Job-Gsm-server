package com.project.JobGsm.global.exception.handler;

import com.project.JobGsm.global.exception.ErrorCode;
import com.project.JobGsm.global.exception.ErrorResponse;
import com.project.JobGsm.global.exception.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> DuplcateEmailException(HttpServletRequest request, DuplicateEmailException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorResponse> PasswordNotMatchException(HttpServletRequest request, PasswordNotMatchException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> InvalidTokenException(HttpServletRequest request, InvalidTokenException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> ExpiredTokenException(HttpServletRequest request, ExpiredTokenException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(KeyNotCorrectException.class)
    public ResponseEntity<ErrorResponse> KeyNotCorrectException(HttpServletRequest request, KeyNotCorrectException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RefreshTokenExpirationException.class)
    public ResponseEntity<ErrorResponse> RefreshTokenExpirationException(HttpServletRequest request, RefreshTokenExpirationException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ErrorResponse> BoardNotFoundException(HttpServletRequest request, BoardNotFoundException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NoBoardException.class)
    public ResponseEntity<ErrorResponse> NoBoardException(HttpServletRequest request, NoBoardException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> CommentNotFoundException(HttpServletRequest request, CommentNotFoundException e) {
        printException(request, e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getField()).append(" : ");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(", "));
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        ErrorResponse errorResponse = new ErrorResponse(stringBuilder.toString(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public void printException(HttpServletRequest request, ErrorCode errorCode) {
        log.error(request.getRequestURI() + " 에서 { " + errorCode + " } 발생");
    }
}
