package com.project.JobGsm.domain.board.controller;

import com.project.JobGsm.domain.board.dto.request.BoardDto;
import com.project.JobGsm.domain.board.dto.request.GetBoardDto;
import com.project.JobGsm.domain.board.service.BoardService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import com.project.JobGsm.global.response.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    /*
    삭제 상세보기(view 증가) 게시글 전체 보기
     */

    @PostMapping("write/board")
    public CommonResultResponse writeBoard(
            @RequestPart(value = "boardDto") BoardDto boardDto,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        boardService.writeBoard(boardDto, file);
        return responseService.getSuccessResult();
    }

    @PatchMapping("update/board/{board_id}")
    public CommonResultResponse updateBoard(
            @RequestPart(value = "boardDto") BoardDto updateBoardDto,
            @RequestPart(value = "image", required = false) MultipartFile file,
            @PathVariable(name = "board_id") Long board_id) {
        boardService.updateBoard(board_id, updateBoardDto, file);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("delete/board/{board_id}")
    public CommonResultResponse deleteBoard(@PathVariable Long board_id) {
        boardService.deleteBoard(board_id);
        return responseService.getSuccessResult();
    }

//    @GetMapping("/board")
//    public SingleResult<Page<GetBoardDto>> getAllBoard() {
//
//    }

    @GetMapping("board/{board_id}")
    public SingleResult<GetBoardDto> getBoardById(@PathVariable Long board_id) {
        GetBoardDto result = boardService.getBoardById(board_id);
        return responseService.getSingleResult(result);
    }
}
