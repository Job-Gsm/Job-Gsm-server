package com.project.JobGsm.domain.user.service.exception;

import com.project.JobGsm.domain.user.dto.request.SignInDto;
import com.project.JobGsm.domain.user.dto.request.SignUpDto;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.exception.exceptions.DuplicateEmailException;
import com.project.JobGsm.global.exception.exceptions.PasswordNotMatchException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(DuplicateEmailException.class, () -> {
            userService.signup(signUpDto2);
        });
    }

    @Test
    @DisplayName("사용자 찾을 수 없을 때 예외 테스트")
    void UserNotFoundException() {

        // given
        SignInDto signInDto = SignInDto.builder()
                .email("s21000@gsm.hs.kr")
                .password("123456")
                .build();

        // when // then
        assertThrows(UserNotFoundException.class, () -> {
            userService.signin(signInDto);
        });

    }

    @Test
    @DisplayName("비밀번호 틀릴 때 테스트")
    void PasswordNotCorrectException() {

        // given
        SignInDto signInDto = SignInDto.builder()
                .email("s21025@gsm.hs.kr")
                .password("12345678")
                .build();

        // when // then
        assertThrows(PasswordNotMatchException.class, () -> {
            userService.signin(signInDto);
        });

    }

}
