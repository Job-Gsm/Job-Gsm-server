package com.project.JobGsm.domain.comment.service;

import com.project.JobGsm.domain.comment.dto.CommentDto;
import com.project.JobGsm.domain.comment.repository.CommentRepository;
import com.project.JobGsm.domain.user.dto.request.SignInDto;
import com.project.JobGsm.domain.user.enumType.Role;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.util.CurrentUserUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    @DisplayName("인증객체 테스트")
    void 인증객체() {

        // given
        SignInDto signInDto = SignInDto.builder()
                .email("s21023@gsm.hs.kr")
                .password("kimsunggil2005!")
                .build();

        userService.signin(signInDto);

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
        org.junit.jupiter.api.Assertions.assertEquals("s21023@gsm.hs.kr", user);

    }

    @Test
    @DisplayName("댓글 작성 테스트")
    void writeComment() {

        // given
        CommentDto commentDto = CommentDto.builder()
                .content("댓글 테스트2")
                .build();

        // when
        Long comment = commentService.writeComment(commentDto, 1L);

        // then
        Assertions.assertThat(comment).isNotNull();

    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() {

    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteComment() {

        // given // when
        commentService.deleteComment(2L);

        // then
        org.junit.jupiter.api.Assertions.assertNull(commentRepository.findById(2L));

    }

}
