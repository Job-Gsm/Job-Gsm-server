package com.project.JobGsm.domain.comment.service;

import com.project.JobGsm.domain.comment.Comment;
import com.project.JobGsm.domain.comment.dto.CommentDto;

public interface CommentService {

    Long writeComment(CommentDto commentDto, Long board_id);
    void updateCommet(CommentDto commentDto, Long comment_id);
    void deleteComment(Long comment_id);
    Comment findByCommentId(Long comment_id);

}
