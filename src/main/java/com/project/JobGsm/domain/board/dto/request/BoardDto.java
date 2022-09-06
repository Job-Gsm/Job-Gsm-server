package com.project.JobGsm.domain.board.dto.request;

import com.project.JobGsm.domain.board.Board;
import com.project.JobGsm.domain.board.enumType.Major;
import com.project.JobGsm.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardDto {

    private String title;
    private String content;
    private List<Major> majors;
    private String deadline;

    public Board toEntity(User user, String url) {
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .majors(majors)
                .deadline(deadline)
                .url("https://job-gsm-bucket.s3.ap-northeast-2.amazonaws.com/" + url)
                .build();
    }
}
