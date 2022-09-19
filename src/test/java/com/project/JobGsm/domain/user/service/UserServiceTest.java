package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.dto.request.*;
import com.project.JobGsm.domain.user.dto.response.ProfileResponseDto;
import com.project.JobGsm.domain.user.dto.response.SignInResponseDto;
import com.project.JobGsm.domain.user.enumType.Major;
import com.project.JobGsm.domain.user.enumType.Role;
import com.project.JobGsm.domain.user.repository.UserRepository;
import com.project.JobGsm.global.util.CurrentUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    @DisplayName("인증객체 테스트")
    void currentUser() {

        // given
        SignInDto signInDto = SignInDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("kimsunggil2005!")
                .build();

        SignInResponseDto login = userService.signin(signInDto);

        // when
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                signInDto.getEmail(),
                signInDto.getPassword(),
                List.of(new SimpleGrantedAuthority(Role.ROLE_USER.getAuthority()))
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(usernamePasswordAuthenticationToken);

        // then
        String user = CurrentUserUtil.getCurrentUserEmail();
        Assertions.assertEquals("s21023@gsm.hs.kr", user);

    }

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
                .password("kimsunggil2005!")
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
                .key("48937")
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

    @Test
    @DisplayName("유저 정보 업데이트 테스트")
    void userInformationDto() {

        // given
        UserInformationDto userInformationDto = UserInformationDto.builder()
                .email("s21023@gsm.hs.kr")
                .username("유환빈")
                .discord("성길#0091")
                .github("SungGil-5125")
                .career(3)
                .build();

        // when // then
        userService.updateUserInformation(userInformationDto);

    }

    @Test
    @DisplayName("전공선택 테스트")
    void selectMajor() {

        // given
        SelectMajorDto selectMajorDto = SelectMajorDto.builder()
                .email("s21023@gsm.hs.kr")
                .major(Major.findByCode("front-end"))
                .build();

        System.out.println("selectMajorDto = " + selectMajorDto.getMajor());

        // when // then
        userService.selectMajor(selectMajorDto);

    }

    @Test
    @DisplayName("프로필 상세보기 테스트")
    void findByUserId() {

        // given // when
        ProfileResponseDto user = userService.findByUserId();

        // then
        assertThat(user).isNotNull();
    }
}