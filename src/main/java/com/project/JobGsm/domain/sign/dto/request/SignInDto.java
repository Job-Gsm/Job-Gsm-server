package com.project.JobGsm.domain.sign.dto.request;

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
public class SignInDto {

    @Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr$", message = "학교계정을 입력해주세요")
    @NotBlank
    private String email;

    @Size(min = 4, max = 15)
    @NotBlank
    private String password;

}
