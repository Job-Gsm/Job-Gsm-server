package com.project.JobGsm.domain.comment.service;

import com.project.JobGsm.domain.board.service.BoardService;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

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
                .content("댓글 테스트")
                .build();

        // when
        Long comment = commentService.writeComment(commentDto, 1L);

        // then
        assertThat(comment).isNotNull();

    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() {

        // given
        CommentDto commentDto = CommentDto.builder()
                .content("댓글 업로드 테스트")
                .build();

        // when // then
        commentService.updateCommet(commentDto, 1L);

    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteComment() {

        // given // when
        commentService.deleteComment(3L);

        // then
        assertThat(commentRepository.findById(3L)).isEmpty();

    }

    @Test
    @DisplayName("구직광고 삭제 했을 때 댓글도 삭제되는가 테스트")
    void deleteBoardAndComment() {

        // given // when
        boardService.deleteBoard(1L);

        // then
        assertThat(commentRepository.findById(4L)).isEmpty();

    }

}
