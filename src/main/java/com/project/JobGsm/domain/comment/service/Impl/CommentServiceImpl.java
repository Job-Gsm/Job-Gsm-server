package com.project.JobGsm.domain.comment.service.Impl;

import com.project.JobGsm.domain.board.Board;
import com.project.JobGsm.domain.board.service.BoardService;
import com.project.JobGsm.domain.comment.Comment;
import com.project.JobGsm.domain.comment.dto.CommentDto;
import com.project.JobGsm.domain.comment.repository.CommentRepository;
import com.project.JobGsm.domain.comment.service.CommentService;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.global.exception.exceptions.CommentNotFoundException;
import com.project.JobGsm.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.project.JobGsm.global.exception.ErrorCode.COMMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BoardService boardService;
    private final CommentRepository commentRepository;
    private final CurrentUserUtil currentUserUtil;

    @Override
    @Transactional
    public Long writeComment(CommentDto commentDto, Long board_id) {

        User user = currentUserUtil.getCurrentUser();
        Board board = boardService.findByBoardId(board_id);
        return commentRepository.save(commentDto.toEntity(user, board)).getComment_id();

    }

    @Override
    public void updateCommet(CommentDto commentDto, Long comment_id) {

        Comment comment = findByCommentId(comment_id);

        comment.updateContent(commentDto.getContent());

    }

    @Override
    public void deleteComment(Long comment_id) {

        Comment comment = findByCommentId(comment_id);
        commentRepository.delete(comment);

    }

    @Override
    public Comment findByCommentId(Long comment_id) {
        return commentRepository.findById(comment_id)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));
    }


}
