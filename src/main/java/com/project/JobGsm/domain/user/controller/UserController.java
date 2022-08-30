package com.project.JobGsm.domain.user.controller;

import com.project.JobGsm.domain.user.dto.request.UserSignInDto;
import com.project.JobGsm.domain.user.dto.request.UserSignUpDto;
import com.project.JobGsm.domain.user.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {

    private final ResponseService responseService;
    private final UserService userService;

    @PostMapping("/signup")
    public CommonResultResponse signup(@RequestBody UserSignUpDto userSignUpDtoDto) {
        userService.signup(userSignUpDtoDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/signin")
    public CommonResultResponse signin(@RequestBody UserSignInDto userSignInDto) {
        UserSignInResponseDto result = userService.signin(userSignInDto);
        return responseService.getSingleResult(result);
    }
}
