package com.project.JobGsm.domain.board.dto.request;

import com.project.JobGsm.domain.board.enumType.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBoardDto {

    private String title;
    private String content;
    private List<Major> majors;
    private String deadline;

}
