package com.project.JobGsm.domain.comment.controller;

import com.project.JobGsm.domain.comment.dto.CommentDto;
import com.project.JobGsm.domain.comment.service.CommentService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/")
public class CommentController {

    private final CommentService commentService;
    private final ResponseService responseService;

    @PostMapping("comment/{board_id}")
    public CommonResultResponse writeComment(@PathVariable("board_id") Long board_id, @RequestBody CommentDto commentDto) {
        commentService.writeComment(commentDto, board_id);
        return responseService.getSuccessResult();
    }

    @PatchMapping("comment/{comment_id}")
    public CommonResultResponse updateComment(@PathVariable("comment_id") Long comment_id, @RequestBody CommentDto commentDto) {
        commentService.updateCommet(commentDto, comment_id);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("comment/{comment_id}")
    public CommonResultResponse deleteComment(@PathVariable("comment_id") Long comment_id) {
        commentService.deleteComment(comment_id);
        return responseService.getSuccessResult();
    }
}
