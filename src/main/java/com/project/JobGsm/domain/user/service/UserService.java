package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.dto.request.*;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.user.dto.response.SignInResponseDto;
import com.project.JobGsm.domain.user.dto.response.UserSignInResponseDto;

import java.util.Map;

public interface UserService {

    Long signup(SignUpDto signUpDto);
    SignInResponseDto signin(SignInDto signInDto);
    Map<String, String> createToken(User user);
    String signupSendEmail(EmailDto emailDto);
    void forgotPasswordSendEmail(EmailDto emailDto);
    void checkEmailKey(CheckEmailKeyDto checkEmailKeyDto);
    String changePassword(ChangePasswordDto changePasswordDto);
}
