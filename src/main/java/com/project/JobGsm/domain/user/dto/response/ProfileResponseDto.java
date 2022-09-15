package com.project.JobGsm.domain.user.dto.response;

import com.project.JobGsm.domain.user.enumType.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponseDto {

    private String name;
    private String email;
    private String discord;
    private String github;
    private Major major;
    private int career;

}
