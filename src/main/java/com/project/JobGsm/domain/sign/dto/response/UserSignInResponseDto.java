package com.project.JobGsm.domain.sign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {

    private Map<String, String> token;

}
