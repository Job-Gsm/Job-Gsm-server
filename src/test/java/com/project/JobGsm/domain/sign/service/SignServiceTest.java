package com.project.JobGsm.domain.sign.service;

import com.project.JobGsm.domain.sign.dto.request.SignInDto;
import com.project.JobGsm.domain.sign.dto.request.SignUpDto;
import com.project.JobGsm.domain.sign.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SignServiceTest {

    @Autowired private SignService signService;
    @Autowired private UserRepository userRepository;

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
        Long user = signService.signup(signUpDto);

        // when
        assertThat(user).isEqualTo(userRepository.findByEmail(signUpDto.getEmail()).orElseThrow().getUser_id());
    }

    @Test
    @DisplayName("로그인 테스트")
    void signin() {

        // given
        SignInDto signInDto = SignInDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("kimsunggil2005!")
                .build();

        // when
        UserSignInResponseDto user = signService.signin(signInDto);

        // then
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("회원가입 이메일 테스트")
    void signupEmail() {


    }

}