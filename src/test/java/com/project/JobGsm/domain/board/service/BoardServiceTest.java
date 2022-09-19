package com.project.JobGsm.domain.board.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.JobGsm.domain.board.dto.request.BoardDto;
import com.project.JobGsm.domain.board.enumType.Major;
import com.project.JobGsm.domain.user.dto.request.SignUpDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("구직광고 업로드 테스트")
    void writeBoard() {

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "imgae.jpeg", "image/jpeg", "<<jpeg>> data>>".getBytes());

        boardService.writeBoard(
                BoardDto.builder()
                        .title("test")
                        .content("test")
                        .deadline("20220910")
                        .majors(List.of(Major.BACK_END))
                        .build(), mockMultipartFile
                );


    }

    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }

    @Test
    void getAllBoard() {
    }

    @Test
    void getBoardById() {
    }
}