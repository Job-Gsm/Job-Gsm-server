package com.project.JobGsm.domain.user.service.exception;

import com.project.JobGsm.domain.user.dto.request.SignUpDto;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.exception.exceptions.DuplicateEmailException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserExceptionTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("중복 이메일 예외 테스트")
    void DuplicateExceptionTest() {

        // given
        SignUpDto signUpDto1 = SignUpDto.builder()
                .email("s21025@gsm.hs.kr")
                .password("123456")
                .build();

        SignUpDto signUpDto2 = SignUpDto.builder()
                .email("s21025@gsm.hs.kr")
                .password("123456")
                .build();

        // when
        userService.signup(signUpDto1);

        // then
        Assertions.assertThrows(DuplicateEmailException.class, () -> {
            userService.signup(signUpDto2);
        });
    }


}
