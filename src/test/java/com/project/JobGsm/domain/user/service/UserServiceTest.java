package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.dto.request.UserSignInDto;
import com.project.JobGsm.domain.user.dto.request.UserSignUpDto;
import com.project.JobGsm.domain.user.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void signup() {

        // given
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("kimsunggil2005!")
                .username("김성길")
                .build();

        // then
        Long user = userService.signup(userSignUpDto);

        // when
        assertThat(user).isEqualTo(userRepository.findByEmail(userSignUpDto.getEmail()).orElseThrow().getUser_id());
    }

    @Test
    @DisplayName("로그인 테스트")
    void signin() {

        // given
        UserSignInDto userSignInDto = UserSignInDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("kimsunggil2005!")
                .build();

        // when
        UserSignInResponseDto user = userService.signin(userSignInDto);

        // then
        assertThat(user).isNotNull();
    }

}