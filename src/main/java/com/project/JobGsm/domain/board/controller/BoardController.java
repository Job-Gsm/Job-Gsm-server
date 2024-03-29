package com.project.JobGsm.domain.board.controller;

import com.project.JobGsm.domain.board.dto.request.BoardDto;
import com.project.JobGsm.domain.board.dto.response.GetBoardDto;
import com.project.JobGsm.domain.board.service.BoardService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import com.project.JobGsm.global.response.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    @PostMapping("board")
    public CommonResultResponse writeBoard(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @RequestPart(value = "boardDto") BoardDto boardDto){
        boardService.writeBoard(boardDto, file);
        return responseService.getSuccessResult();
    }

    @PatchMapping("board/{board_id}")
    public CommonResultResponse updateBoard(
            @RequestPart(value = "boardDto") BoardDto updateBoardDto,
            @RequestPart(value = "image", required = false) MultipartFile file,
            @PathVariable(name = "board_id") Long board_id) {
        boardService.updateBoard(board_id, updateBoardDto, file);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("board/{board_id}")
    public CommonResultResponse deleteBoard(@PathVariable Long board_id) {
        boardService.deleteBoard(board_id);
        return responseService.getSuccessResult();
    }

    @GetMapping("/board")
    public SingleResult<Page<GetBoardDto>> getAllBoard(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GetBoardDto> result = boardService.getAllBoard(pageable);
        return responseService.getSingleResult(result);
    }

    @GetMapping("board/{board_id}")
    public SingleResult<GetBoardDto> getBoardById(@PathVariable Long board_id) {
        GetBoardDto result = boardService.getBoardById(board_id);
        return responseService.getSingleResult(result);
    }
}
