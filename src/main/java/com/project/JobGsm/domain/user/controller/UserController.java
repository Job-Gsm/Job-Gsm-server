package com.project.JobGsm.domain.user.controller;

import com.project.JobGsm.domain.user.dto.request.*;
import com.project.JobGsm.domain.user.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class UserController {

    private final ResponseService responseService;
    private final UserService userService;

    @PostMapping("signup")
    public CommonResultResponse signup(@Valid @RequestBody SignUpDto signUpDtoDto) {
        userService.signup(signUpDtoDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("signin")
    public CommonResultResponse signin(@Valid @RequestBody SignInDto signInDto) {
        UserSignInResponseDto result = userService.signin(signInDto);
        return responseService.getSingleResult(result);
    }

    @PostMapping("signup/send/email")
    public CommonResultResponse signupSendEmail(@Valid @RequestBody EmailDto emailDto) {
        userService.signupSendEmail(emailDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("password/send/email")
    public CommonResultResponse forgotPasswordSendEmail(@Valid @RequestBody EmailDto emailDto) {
        userService.forgotPasswordSendEmail(emailDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("check/email")
    public CommonResultResponse checkEmail(@Valid @RequestBody CheckEmailKeyDto checkEmailKeyDto) {
        userService.checkEmailKey(checkEmailKeyDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("change/password")
    public CommonResultResponse changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
        return responseService.getSuccessResult();
    }

//    @PostMapping("select/major")
//    public CommonResultResponse selectMajor(@RequestBody SelectMajorDto selectMajorDto) {
//
//    }

    @GetMapping("test")
    public String test() {
        return "선민재 병신";
    }

}
