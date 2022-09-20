package com.project.JobGsm.domain.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInformationDto {

    @Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr$", message = "학교계정을 입력해주세요")
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String github;

    @Pattern(regexp = "/#/", message = "다시 확인해주세요.")
    @NotBlank
    private String discord;

}
