package com.project.JobGsm.domain.board.dto.request;

import com.project.JobGsm.domain.board.enumType.Major;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class GetBoardDto {

    private String title;
    private String content;
    private List<Major> majors;
    private String deadline;
    private String url;
    private String view;

}
