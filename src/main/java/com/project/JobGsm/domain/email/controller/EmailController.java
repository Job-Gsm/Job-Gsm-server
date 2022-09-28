package com.project.JobGsm.domain.email.controller;

import com.project.JobGsm.domain.email.dto.CheckEmailKeyDto;
import com.project.JobGsm.domain.email.dto.EmailDto;
import com.project.JobGsm.domain.email.service.EmailService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class EmailController {

    private final EmailService emailService;
    private final ResponseService responseService;

    @PostMapping("send/email")
    public CommonResultResponse sendEmail(@Valid @RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("check/email")
    public CommonResultResponse checkEmail(@Valid @RequestBody CheckEmailKeyDto checkEmailKeyDto) {
        emailService.checkEmailKey(checkEmailKeyDto);
        return responseService.getSuccessResult();
    }
}
