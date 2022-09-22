package com.project.JobGsm.domain.board.service.Impl;

import com.project.JobGsm.domain.board.Board;
import com.project.JobGsm.domain.board.dto.request.BoardDto;
import com.project.JobGsm.domain.board.dto.response.GetBoardDto;
import com.project.JobGsm.domain.board.repository.BoardRepository;
import com.project.JobGsm.domain.board.service.BoardService;
import com.project.JobGsm.domain.board.service.S3Service;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.global.exception.ErrorCode;
import com.project.JobGsm.global.exception.exceptions.BoardNotFoundException;
import com.project.JobGsm.global.exception.exceptions.NoBoardException;
import com.project.JobGsm.global.util.CurrentUserUtil;
import com.project.JobGsm.global.util.ResponseDtoUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import static com.project.JobGsm.global.exception.ErrorCode.NO_BOARD;

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
        Board board = findByBoardId(board_id);

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

        Board board = findByBoardId(board_id);
        
        boardRepository.delete(board);

    }

    @Override
    @Transactional
    public Page<GetBoardDto> getAllBoard(Pageable pageable) {

        Page<Board> boardPage = boardRepository.findAll(pageable);

        if(boardPage.isEmpty()) {
            throw new NoBoardException(NO_BOARD);
        }

        return boardPage.map(board -> {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(board, GetBoardDto.class);
        });
    }

    @Override
    @Transactional
    public GetBoardDto getBoardById(Long board_id) {
        Board board = findByBoardId(board_id);

        boardRepository.updateViewBoard(board_id);

        return ResponseDtoUtil.map(board, GetBoardDto.class);

    }

    @Override
    public Board findByBoardId(Long board_id) {
        return boardRepository.findById(board_id)
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));
    }

}
