package com.project.JobGsm.domain.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckEmailKeyDto {

    @Size(min = 5, max = 5)
    @NotBlank
    private String authKey;

    private String email;

}
