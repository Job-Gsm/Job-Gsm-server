package com.project.JobGsm.domain.comment.dto;

import com.project.JobGsm.domain.board.Board;
import com.project.JobGsm.domain.comment.Comment;
import com.project.JobGsm.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentDto {

    @NotBlank
    private String content;

    public Comment toEntity(User user, Board board) {
        return Comment.builder()
                .content(content)
                .board(board)
                .user(user)
                .build();
    }
}
