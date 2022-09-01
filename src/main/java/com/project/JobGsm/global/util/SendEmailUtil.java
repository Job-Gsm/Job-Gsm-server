package com.project.JobGsm.global.util;

import com.project.JobGsm.global.exception.exceptions.KeyNotCorrectException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.project.JobGsm.global.exception.ErrorCode.KEY_NOT_CORRECT;

@Service
@RequiredArgsConstructor
public class SendEmailUtil {

    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;

    public void sendEmailText(String email, String authKey) {

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

        redisUtil.setDataExpire(authKey, email, 60 * 5L);
    }

    public void checkEmailKey(String key) {
        if(redisUtil.getData(key) == null) {
            throw new KeyNotCorrectException(KEY_NOT_CORRECT);
        }
    }
}
