package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.dto.request.*;
import com.project.JobGsm.domain.user.dto.response.SignInResponseDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    void signup() {

        // given
        SignUpDto signUpDto = SignUpDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("kimsunggil2005!")
                .username("김성길")
                .build();

        // then
        Long user = userService.signup(signUpDto);

        // when
        assertThat(user).isEqualTo(userRepository.findByEmail(signUpDto.getEmail()).orElseThrow().getUser_id());
    }

    @Test
    @DisplayName("로그인 테스트")
    void signin() {

        // given
        SignInDto signInDto = SignInDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("1234")
                .build();

        // when
        SignInResponseDto user = userService.signin(signInDto);

        // then
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("이메일 발송 테스트")
    void signupEmail() {

        // given
        EmailDto emailDto = EmailDto.builder()
                .email("s21023@gsm.hs.kr")
                .build();

        // when
        String authKey = userService.signupSendEmail(emailDto);
        // String authKey = signService.forgotPasswordSendEmail(emailDto);
        System.out.println("authKey = " + authKey);

        // then
        assertThat(authKey).isNotNull();

    }

    @Test
    @DisplayName("인증키 확인 테스트")
    void checkEmail() {

        // given
        CheckEmailKeyDto checkEmailKeyDto = CheckEmailKeyDto.builder()
                .key("29931")
                .build();

        // when, then
        userService.checkEmailKey(checkEmailKeyDto);

    }

    @Test
    @DisplayName("비밀번호 변경 테스트")
    void changePassword() {

        // given
        ChangePasswordDto changePasswordDto = ChangePasswordDto.builder()
                .email("s21023@gsm.hs.kr")
                .newPassword("1234")
                .build();

        // when
        String password = userService.changePassword(changePasswordDto);

        // then
        assertThat(passwordEncoder.matches(changePasswordDto.getNewPassword(), password)).isTrue();
    }
}