package com.project.JobGsm.domain.user.dto.request;


import com.project.JobGsm.domain.user.enumType.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectMajorDto {

    @Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr$", message = "학교계정을 입력해주세요")
    @NotBlank
    private String email;

    @NotBlank
    private Major major;

    @NotBlank
    private String github;

    @NotBlank
    private String discord;

    @Size(min = 1, max = 7)
    @NotBlank
    private int career;

}
