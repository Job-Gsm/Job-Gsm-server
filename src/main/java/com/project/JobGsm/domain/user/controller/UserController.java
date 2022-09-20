package com.project.JobGsm.domain.user.controller;

import com.project.JobGsm.domain.user.dto.request.*;
import com.project.JobGsm.domain.user.dto.response.ProfileResponseDto;
import com.project.JobGsm.domain.user.dto.response.SignInResponseDto;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        SignInResponseDto result = userService.signin(signInDto);
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

    @PostMapping("information")
    public CommonResultResponse updateUserInformation(@Valid @RequestBody UserInformationDto userInformationDto) {
        userService.updateUserInformation(userInformationDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("select/major")
    public CommonResultResponse selectMajor(@Valid @RequestBody SelectMajorDto selectMajorDto) {
        userService.selectMajor(selectMajorDto);
        return responseService.getSuccessResult();
    }

    @PatchMapping("profile/image")
    public CommonResultResponse uploadProfileImage(@RequestPart(value = "image") MultipartFile file) {
        userService.uploadProfileImage(file);
        return responseService.getSuccessResult();
    }

    @GetMapping()
    public CommonResultResponse currentUserProfile() {
        ProfileResponseDto result = userService.currentUser();
        return responseService.getSingleResult(result);
    }

    @GetMapping("{user_id}")
    public CommonResultResponse findByUserIdProfile(@PathVariable Long user_id) {
        ProfileResponseDto result = userService.findByUserId(user_id);
        return responseService.getSingleResult(result);
    }

    @GetMapping("test")
    public String test() {
        return "선민재 병신";
    }

}
