package com.project.JobGsm.domain.comment;

import com.project.JobGsm.domain.board.Board;
import com.project.JobGsm.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Board board;

    @Column
    private String content;

    public void updateContent(String content) {
        this.content = content != null ? content : this.content;
    }
}
