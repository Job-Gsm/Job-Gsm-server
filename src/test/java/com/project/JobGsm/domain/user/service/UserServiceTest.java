package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.dto.request.UserSignUpDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
    void register() {

        // given
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("kimsunggil2005!")
                .username("김성길")
                .build();

        // then
        Long user = userService.signup(userSignUpDto);

        // when
        Assertions.assertThat(user).isEqualTo(userRepository.findByEmail(userSignUpDto.getEmail()).orElseThrow().getUser_id());
    }

}