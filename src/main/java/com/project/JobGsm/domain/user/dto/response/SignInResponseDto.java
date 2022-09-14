package com.project.JobGsm.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class SignInResponseDto {

    private Map<String, String> token;

}
