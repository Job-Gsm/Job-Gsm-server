package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.dto.request.RefreshTokenDto;
import com.project.JobGsm.domain.user.dto.request.SignInDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import com.project.JobGsm.global.util.CurrentUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RefreshTokenServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserUtil currentUserUtil;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("토큰 재발급 테스트")
    void refreshTokenTest() {

        // given
        SignInDto signInDto = SignInDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("1234")
                .build();

        RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder()
                .email("s21023@gsm.hs.kr")
                .build();


        // when
        userService.signin(signInDto);
        String refreshToken = currentUserUtil.getCurrentUser().getRefreshToken();
        System.out.println("refreshToken = " + refreshToken);

        // then
        Assertions.assertDoesNotThrow(() -> refreshTokenService.refreshToken(refreshToken, refreshTokenDto));

    }
}
