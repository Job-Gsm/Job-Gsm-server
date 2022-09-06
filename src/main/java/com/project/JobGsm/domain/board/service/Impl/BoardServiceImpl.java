package com.project.JobGsm.domain.board.service.Impl;

import com.project.JobGsm.domain.board.Board;
import com.project.JobGsm.domain.board.dto.request.BoardDto;
import com.project.JobGsm.domain.board.dto.request.GetBoardDto;
import com.project.JobGsm.domain.board.repository.BoardRepository;
import com.project.JobGsm.domain.board.service.BoardService;
import com.project.JobGsm.domain.board.service.S3Service;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.global.exception.ErrorCode;
import com.project.JobGsm.global.exception.exceptions.BoardNotFoundException;
import com.project.JobGsm.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final CurrentUserUtil currentUserUtil;
    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    @Override
    @Transactional
    public void writeBoard(BoardDto boardDto, MultipartFile file) {

        User user = currentUserUtil.getCurrentUser();
        String uploadFile = null;

        try {
            uploadFile = s3Service.upload(file, "board_image/");
            boardRepository.save(boardDto.toEntity(user, uploadFile));
        } catch (NullPointerException e) {
            boardRepository.save(boardDto.toEntity(user, uploadFile));
        }
    }

    @Override
    @Transactional
    public void updateBoard(Long board_id, BoardDto updateBoardDto, MultipartFile file) {
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));
        String uploadFile = null;

        try {
            uploadFile = s3Service.upload(file, "board_image/");
            s3Service.deleteFile(board.getUrl());
            board.updateBoard(updateBoardDto.getTitle(), updateBoardDto.getContent(), updateBoardDto.getMajors(), updateBoardDto.getDeadline(), uploadFile);
        } catch (NullPointerException e) {
            board.updateBoard(updateBoardDto.getTitle(), updateBoardDto.getContent(), updateBoardDto.getMajors(), updateBoardDto.getDeadline(), uploadFile);
        }
    }

    @Override
    @Transactional
    public void deleteBoard(Long board_id) {
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));
        
        boardRepository.delete(board);
    }

//    @Override
//    public Page<GetBoardDto> getAllBoard() {
//
//    }

    @Override
    @Transactional
    public GetBoardDto getBoardById(Long board_id) {
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(board, GetBoardDto.class);

    }
}
