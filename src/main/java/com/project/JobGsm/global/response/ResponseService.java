package com.project.JobGsm.global.response;

import com.project.JobGsm.global.response.result.CommonResultResponse;
import com.project.JobGsm.global.response.result.SingleResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Getter
    @AllArgsConstructor
    public enum CommonResponse {
        SUCCESS(true, "성공", HttpStatus.OK);

        private boolean success;
        private String message;
        private HttpStatus httpStatus;
    }

    public CommonResultResponse getSuccessResult() {
        return getCommonResultResponse();
    }

    public <T> SingleResult<T> getSingleResult(T result) {
        return new SingleResult<T>(getCommonResultResponse(), result);
    }

    public CommonResultResponse getCommonResultResponse() {
        return new CommonResultResponse(CommonResponse.SUCCESS.isSuccess(), CommonResponse.SUCCESS.getMessage(), CommonResponse.SUCCESS.getHttpStatus());
    }
}
