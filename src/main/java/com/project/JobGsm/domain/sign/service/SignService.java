package com.project.JobGsm.domain.sign.service;

import com.project.JobGsm.domain.sign.User;
import com.project.JobGsm.domain.sign.dto.request.CheckEmailKeyDto;
import com.project.JobGsm.domain.sign.dto.request.SignInDto;
import com.project.JobGsm.domain.sign.dto.request.SignUpDto;
import com.project.JobGsm.domain.sign.dto.request.SignUpEmailDto;
import com.project.JobGsm.domain.sign.dto.response.UserSignInResponseDto;

import java.util.Map;

public interface SignService {

    Long signup(SignUpDto signUpDto);
    UserSignInResponseDto signin(SignInDto signInDto);
    Map<String, String> createToken(User user);
    String signupEmail(SignUpEmailDto signUpEmailDto);
    void checkEmailKey(CheckEmailKeyDto checkEmailKeyDto);
}
