package com.project.JobGsm.domain.sign.controller;

import com.project.JobGsm.domain.sign.dto.request.*;
import com.project.JobGsm.domain.sign.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.sign.service.SignService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class SignController {

    private final ResponseService responseService;
    private final SignService signService;

    @PostMapping("signup")
    public CommonResultResponse signup(@Valid @RequestBody SignUpDto signUpDtoDto) {
        signService.signup(signUpDtoDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("signin")
    public CommonResultResponse signin(@Valid @RequestBody SignInDto signInDto) {
        UserSignInResponseDto result = signService.signin(signInDto);
        return responseService.getSingleResult(result);
    }

    @PostMapping("signup/send/email")
    public CommonResultResponse signupSendEmail(@Valid @RequestBody EmailDto emailDto) {
        signService.forgotPasswordSendEmail(emailDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("send/email")
    public CommonResultResponse forgotPasswordSendEmail(@Valid @RequestBody EmailDto emailDto) {
        signService.signupSendEmail(emailDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("check/email")
    public CommonResultResponse checkEmail(@Valid @RequestBody CheckEmailKeyDto checkEmailKeyDto) {
        signService.checkEmailKey(checkEmailKeyDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("change/password")
    public CommonResultResponse changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        signService.changePassword(changePasswordDto);
        return responseService.getSuccessResult();
    }

    @GetMapping("test")
    public String test() {
        return "임가람 병신";
    }
}
