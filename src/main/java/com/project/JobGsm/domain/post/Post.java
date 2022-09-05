package com.project.JobGsm.domain.post;

import com.project.JobGsm.domain.post.enumType.Major;
import com.project.JobGsm.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column
    private String title;

    @Column
    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "major", joinColumns = @JoinColumn(name = "post_id"))
    @Column
    private List<Major> major;

    // 개발 기한
    @Column
    private String deadline;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;


}
