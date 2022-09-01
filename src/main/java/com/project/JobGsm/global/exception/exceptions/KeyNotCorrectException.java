package com.project.JobGsm.global.exception.exceptions;

import com.project.JobGsm.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyNotCorrectException extends RuntimeException {

    private ErrorCode errorCode;

}
