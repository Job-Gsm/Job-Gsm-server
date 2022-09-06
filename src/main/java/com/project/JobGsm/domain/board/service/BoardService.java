package com.project.JobGsm.domain.board.service;

import com.project.JobGsm.domain.board.dto.request.BoardDto;
import com.project.JobGsm.domain.board.dto.request.GetBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

    void writeBoard(BoardDto boardDto, MultipartFile file);
    void updateBoard(Long board_id, BoardDto updateBoardDto, MultipartFile file);

    void deleteBoard(Long board_id);
//    Page<GetBoardDto> getAllBoard();
    GetBoardDto getBoardById(Long board_id);
}
