package com.project.JobGsm.domain.sign.service;

import com.project.JobGsm.domain.sign.dto.request.*;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.sign.dto.response.UserSignInResponseDto;

import javax.validation.constraints.Email;
import java.util.Map;

public interface SignService {

    Long signup(SignUpDto signUpDto);
    UserSignInResponseDto signin(SignInDto signInDto);
    Map<String, String> createToken(User user);
    String signupSendEmail(EmailDto emailDto);
    String forgotPasswordSendEmail(EmailDto emailDto);
    void checkEmailKey(CheckEmailKeyDto checkEmailKeyDto);
    String changePassword(ChangePasswordDto changePasswordDto);
}
