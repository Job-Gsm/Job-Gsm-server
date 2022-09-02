package com.project.JobGsm.global.exception.exceptions;

import com.project.JobGsm.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshTokenExpirationException extends RuntimeException {

    private ErrorCode errorCode;

}
