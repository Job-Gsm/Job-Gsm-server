package com.project.JobGsm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
