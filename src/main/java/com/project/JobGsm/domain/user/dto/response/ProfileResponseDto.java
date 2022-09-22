package com.project.JobGsm.domain.user.dto.response;

import com.project.JobGsm.domain.user.enumType.Major;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class ProfileResponseDto {

    private String username;
    private String email;
    private String discord;
    private String github;
    private Major major;
    private int career;

}
