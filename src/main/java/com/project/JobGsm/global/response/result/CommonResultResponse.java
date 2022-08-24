package com.project.JobGsm.global.response.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CommonResultResponse {

    private boolean success;
    private String message;
    private HttpStatus status;

}
