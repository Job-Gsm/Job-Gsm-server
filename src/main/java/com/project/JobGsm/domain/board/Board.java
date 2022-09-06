package com.project.JobGsm.domain.board;

import com.project.JobGsm.domain.board.enumType.Major;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column
    private String title;

    @Column
    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "major", joinColumns = @JoinColumn(name = "post_id"))
    @Column
    private List<Major> majors;

    // 개발 기한
    @Column
    private String deadline;

    @Column
    private String url;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    public void updateBoard(String title, String content, List<Major> majors, String deadline, String url) {
        this.title = title != null ? title : this.title;
        this.content = content != null ? content : this.content;
        this.majors = majors != null ? majors : this.majors;
        this.deadline = deadline != null ? deadline : this.deadline;
        this.url = url != null ? url : this.url;
    }
}
