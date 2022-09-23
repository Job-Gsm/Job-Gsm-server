package com.project.JobGsm.domain.board.service;

import com.project.JobGsm.domain.board.dto.request.BoardDto;
import com.project.JobGsm.domain.board.dto.response.GetBoardDto;
import com.project.JobGsm.domain.board.enumType.Major;
import com.project.JobGsm.domain.board.repository.BoardRepository;
import com.project.JobGsm.domain.user.dto.request.SignInDto;
import com.project.JobGsm.domain.user.enumType.Role;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.util.CurrentUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardRepository boardRepository;

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
    @DisplayName("구직광고 업로드 테스트")
    void writeBoard() {

        // given
        BoardDto boardDto =  BoardDto.builder()
                .title("test")
                .content("test")
                .deadline("20220910")
                .majors(List.of(Major.BACK_END))
                .build();

        // when // then
        boardService.writeBoard(boardDto, null);

    }

    @Test
    @DisplayName("구직광고 업데이트 테스트")
    void updateBoard() {

        // given
        BoardDto boardDto =  BoardDto.builder()
                .title("update test")
                .content("update test")
                .deadline("20220922")
                .majors(List.of(Major.BACK_END))
                .build();

        // when // then
        boardService.updateBoard(1L, boardDto, null);

    }

    @Test
    @DisplayName("구직광고 삭제 테스트")
    void deleteBoard() {

        // given // when
        boardService.deleteBoard(1L);

        // then
        Assertions.assertNull(boardRepository.findById(1L));
    }

    @Test
    @DisplayName("전체 구직광고 보기 테스트")
    void getAllBoard() {

        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<GetBoardDto> allBoard = boardService.getAllBoard(pageable);

        // then
        assertThat(allBoard).isNotNull();

    }

    @Test
    @DisplayName("구직광고 상세보기 테스트")
    void getBoardById() {

        // given // when
        GetBoardDto board = boardService.getBoardById(1L);

        // then
        assertThat(board).isNotNull();

    }
}