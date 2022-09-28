package com.project.JobGsm.domain.email.service.Impl;

import com.project.JobGsm.domain.email.EmailCertification;
import com.project.JobGsm.domain.email.dto.CheckEmailKeyDto;
import com.project.JobGsm.domain.email.dto.EmailDto;
import com.project.JobGsm.domain.email.repository.EmailRepository;
import com.project.JobGsm.domain.email.service.EmailService;
import com.project.JobGsm.global.exception.exceptions.KeyNotCorrectException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import io.lettuce.core.RedisCommandTimeoutException;
import io.lettuce.core.RedisLoadingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Random;

import static com.project.JobGsm.global.exception.ErrorCode.KEY_NOT_CORRECT;
import static com.project.JobGsm.global.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@EnableAsync
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    @Async
    @Override
    public void sendEmail(EmailDto emailDto) {
        sendEmailText(emailDto.getEmail());
    }

    @Transactional
    @Override
    public void sendEmailText(String email) {

        String authKey = createAuthKey(email);

        EmailCertification emailCertification = emailRepository.findById(email)
                .orElse(EmailCertification.builder()
                        .email(email)
                        .authKey(authKey)
                        .certification(false)
                        .build());

        emailRepository.save(emailCertification);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject("[Job-Gsm] 인증번호");
            helper.setText("Job-Gsm 인증번호는 " + authKey + "입니다. 이 인증키를 외부에 노출하지 마세요.");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void checkEmailKey(CheckEmailKeyDto checkEmailKeyDto) {

        try {
            EmailCertification emailCertification = emailRepository.findById(checkEmailKeyDto.getEmail())
                    .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

            if(!emailCertification.getAuthKey().equals(checkEmailKeyDto.getAuthKey())) {
                throw new KeyNotCorrectException(KEY_NOT_CORRECT);
            }

            log.info(emailCertification.getAuthKey());

            emailCertification.updateCertification(true);
        } catch (RedisCommandTimeoutException e) {
            log.info("성공");
        }

    }

    @Override
    public String createAuthKey(String email) {

//        emailRepository.findById(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        Random random = new Random();
        return String.valueOf(random.nextInt(88888) + 11111);
    }

}
