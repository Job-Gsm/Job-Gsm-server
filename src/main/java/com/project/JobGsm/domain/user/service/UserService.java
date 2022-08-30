package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.user.dto.request.UserSignInDto;
import com.project.JobGsm.domain.user.dto.request.UserSignUpDto;
import com.project.JobGsm.domain.user.dto.response.UserSignInResponseDto;

import java.util.Map;

public interface UserService {

    Long signup(UserSignUpDto userSignUpDto);
    UserSignInResponseDto signin(UserSignInDto userSignInDto);
    Map<String, String> createToken(User user);

}
