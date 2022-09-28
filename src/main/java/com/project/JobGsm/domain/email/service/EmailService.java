package com.project.JobGsm.domain.email.service;

import com.project.JobGsm.domain.email.dto.EmailDto;
import com.project.JobGsm.domain.email.dto.CheckEmailKeyDto;

public interface EmailService {

    void sendEmail(EmailDto emailDto);
    void checkEmailKey(CheckEmailKeyDto checkEmailKeyDto);
    void sendEmailText(String email);
    String createAuthKey(String email);
}
